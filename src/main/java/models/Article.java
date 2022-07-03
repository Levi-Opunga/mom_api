package models;

import java.util.Objects;

public class Article {
private  String Author;
private int AuthorId;



 private String Heading;

 private String ArticleContent;
 private int id;

 public Article(String author, int authorId, String articleContent,String heading) {
  Heading = heading;
     Author = author;
  AuthorId = authorId;
  ArticleContent = articleContent;
 }

 public Article() {
 }

 public String getAuthor() {
  return Author;
 }

 public void setAuthor(String author) {
  Author = author;
 }

 public int getAuthorId() {
  return AuthorId;
 }

 public void setAuthorId(int authorId) {
  AuthorId = authorId;
 }



 public String getArticleContent() {
  return ArticleContent;
 }

 public void setArticleContent(String articleContent) {
  ArticleContent = articleContent;
 }
 public String getHeading() {
  return Heading;
 }

 public void setHeading(String heading) {
  Heading = heading;
 }
 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 @Override
 public boolean equals(Object o) {
  if (this == o) return true;
  if (o == null || getClass() != o.getClass()) return false;
  Article article = (Article) o;
  return AuthorId == article.AuthorId && id == article.id && Objects.equals(Author, article.Author) && Objects.equals(Heading, article.Heading) && Objects.equals(ArticleContent, article.ArticleContent);
 }

 @Override
 public int hashCode() {
  return Objects.hash(Author, AuthorId, Heading, ArticleContent, id);
 }
}
