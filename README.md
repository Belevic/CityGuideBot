# CityGuideBot
Application that is used for managing tourist telegram bot. Bot functionality looks like give advices for user questions about different cities.
All data for answering is stored in database. Bot management include add,edit and remove operations with data stored in DB.
REST WebService is used for managing city tour bot.

Yo should have PostgeSQL + Google Chtome + JDK 1.8 + Telegram + Maven 3.6.3 on your computer for starting using this app.

For using application follow next steps:
1) Download zip archive with project from git;
2) Create database PostgreSQL for storing data;
3) Change in db.properties file in Resources on project username,pass and database url for your own;
4) In command line from project root enter Maven command: mvn clean install
5) In command line from project root enter Maven command: java -jar way to project\CountryBotAPI\target\CountryBotAPI-1.0-SNAPSHOT.jar
6) App started.

Check results of application work in Telegram.

bot name: city_quiz_bot

bot token: 1185692796:AAH4_MyuBK6qs3qU2uOhGFtnN-WHom2zpjQ
