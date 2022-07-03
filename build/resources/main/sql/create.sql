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