import requests
import drugbank
import io
import re
import numpy as np


from google.cloud import vision
from google.cloud.vision import types

DRUGS_URI = 'https://api.drugbankplus.com/v1/drug_names?q={:s}&fuzzy=true'
API_KEY = drugbank.credentials['API_KEY']
MAX_PILLS = 500
MIN_PILLS = 19
headers = {'Authorization': API_KEY}
punctuation = '[.,;\'"]'


# [START def_detect_text]
def detect_text(path):
    """Detects text in the file."""
    client = vision.ImageAnnotatorClient()

    # [START migration_text_detection]
    with io.open(path, 'rb') as image_file:
        content = image_file.read()

    image = types.Image(content=content)

    response = client.text_detection(image=image)
    texts = response.text_annotations
    txt = [] # stores text and area of the rectangle surrounding it
    for text in texts:
        vertices = (['({},{})'.format(vertex.x, vertex.y)
                     for vertex in text.bounding_poly.vertices])
        x, y = x_y(text.bounding_poly.vertices)
        txt.append((text.description, PolyArea(x,y)))
    txt.sort(key=lambda x:x[1], reverse=True)
    return txt[1:]


def x_y(vertices):
    x = []
    y = []
    for vertix in vertices:
        x.append(vertix.x)
        y.append(vertix.y)
    return x,y


# from stackoverflow
def PolyArea(x,y):
    return 0.5*np.abs(np.dot(x,np.roll(y,1))-np.dot(y,np.roll(x,1)))


def sanitize(str):
    clean = re.sub(punctuation, '', str)
    return clean


def search(top):
    result = False
    i = 3
    while not result:
        result = _search(' '.join(top[:i]))
        i -= 1
    return result


def _search(d_name):
    r = requests.get(DRUGS_URI.format(d_name), headers=headers)
    try:
        return r.json()['products'][0]['name']
    except IndexError or KeyError:
        return False


# return pills count by looking at the numbers with the biggest area
def _count(texts):
    count = False
    for text in texts:
        try:
            count = int(text[0])
            if count < MAX_PILLS and count > MIN_PILLS:
                return count
        except ValueError:
            continue
    return count


def main():
    text = detect_text('./img/img1.jpg')
    count = _count(text)
    # top 3 detected words
    top = [sanitize(t[0]) for t in text[:3]]
    name = search(top)
    print('Drug: {:s}, {:d} tablets'.format(name, count))


if __name__ == '__main__':
    main()
