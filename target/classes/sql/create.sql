CREATE DATABASE mum_app;
\c mum_app;
Create table articles(
ID  SERIAL PRIMARY KEY,
Heading VARCHAR,
ArticleContent VARCHAR,
Author VARCHAR,
AuthorId int
);
Create table chats(
ID  SERIAL PRIMARY KEY,
message VARCHAR,
ReceiverID int,
senderID int
);
Create table users(
ID  SERIAL PRIMARY KEY,
name VARCHAR,
lastmessage VARCHAR,
lastmsgTime VARCHAR,
phoneno VARCHAR,
country VARCHAR,
imagid int
);