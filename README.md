# Dose

# Inspiration

Every day in the US, 2,500 youth (12 to 17) abuse a prescription pain relievers or over the counter drugs. Prescription drug abuse causes the largest percentage of deaths from drug overdosing. We have an app which attempts to solve this problem as well as provide a convenient way to tracking the pills you take.

# What it does

The app allows the user to see a history of each medication they took and tracks which medications they are currently on as well as keeps a count of how many pills they have left. They also keep track of which medications the user has and will notify the user if a new medication is added and would have negative interactions with their previous drugs. When the pills is at 10% of the original capacity, it gives users option to send a SMS text to their physician for another prescription or provide them access to express script website for a refill.

# How we built it

This entire app was built using Android Studio and java. It uses Google Cloud vision API's image recognition to get the name of the medication. We web scraped through google and the Pub Med site to find additional useful information that could be useful for the user. We also used the drug bank API to see if there are negative interactions between different drugs.

# Challenges we ran into

Due to the number of independent API's we require and data we need to manage, it was very tricky configuring and making successful calls. Also since none of us were experts at Android before hand, debugging was quite difficult as android studios have a large number of files within which could have caused the error.

# Accomplishments that I'm proud of

As 4 members that were all previously unfamiliar with Android studios, we were able to build a function native android app which could help many people with their medications. We were also able to make the user interface quite friendly catering to the user experience.

# What's next for Dose

We plan to implement more features to the applications and put the data on the server in which the users physician can access. Therefore they can directly check whether the patient is taking their meds. We would also love to call onto the express script api to manage the patients refills when they are closed to finishing their medications.
