# Dose


Application for users to keep track of their medication. 
Uses Google's Mobile Vision API to scan barcodes / QR codes on the medication. This goes through a UPC api call gathering the name of the product and count of the bottle. Lastly Pub Med Api is called to gather other relevant information on the drug such as warning, side effects, etc.

The app allows the user to see a history of each medication they took and tracks which medications they are currently on as well as keeps a count of how many pills they have left. Push notifications are sent each day as a reminder to take the pills.

When the pills is at 10% of the original capacity, it gives users option to send a SMS text to their physician for another prescription or provide them access to express script website for a refill.
