In this project, I am creating an app with a temporary name "**_Word Bank_**" to help myself with vocabulary building in English.

**_Word Bank_** has the following features:

**1. Collecting expressions with contextual references**

Instead of importing a list of words from vocabulary books, I would build up a database with words, expressions, and sentences that I come across in my everyday life. In other words, every time I hear someone speaking an expression new to me, I would pull out my app and add it into my personal pool. The rationale behind this practice is that **_contextual information plays a critical role in forming memories_.**  This notion leads to a key idea: I want to use this app to not only register the new expressions, but also record in which way these expressions were used, by whom, in which topic we were discussing, in what tone, etc.

This feature is mostly implemented in the New Entry Activity and Word Page Activity, with DBhelper operating data in a SQL database.

To-do list:
- [x] New entry registration
- [x] Save to database
- [x] Present items in Word List page
- [ ] Update information for each item
- [ ] Add image and video recording


**2. Unplanned daily repetition**

I personally hate notifications as the sound and vibration that come along with them always distract me. But I figured this function might help me to reinforce and consolidate my memory on expressions. This will be implemented by having notifications randomly push items to the home page of the phone, and thus increase the frequency of me "accidentially encountering" these expressions on daily basis. 

This feature is accomplished with alarm receiver, notification, and randomization functions.

To-do list:
- [x] Timer
- [x] Notification
- [ ] Push items according to previous repetion history
