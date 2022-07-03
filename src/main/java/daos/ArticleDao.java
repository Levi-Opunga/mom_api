package daos;

import models.Article;

import java.util.List;

public interface ArticleDao
{
    void addArticle(Article article);
    void removeArticle(int id);
    Article getSpecificArticle(int id);
    List<Article> searchArticle(String search);
    List<Article> getAllArticles();
    void editArticle(Article article);

}
