package daos;

import models.Article;
import org.sql2o.Connection;

import java.util.List;
import java.util.stream.Collectors;

public class ArticlesSql2oDao implements ArticleDao {
    @Override
    public void addArticle(Article article) {
        String sqlStatement = "Insert into articles (heading, articlecontent, author, authorid) values( :heading, :articlecontent, :author,:authorid);";
        try (Connection conn = DatabaseConnection.sql2o.open()) {
            int id = (int) conn.createQuery(sqlStatement, true)
                    .addParameter("heading", article.getHeading())
                    .addParameter("articlecontent", article.getArticleContent())
                    .addParameter("author", article.getAuthor())
                    .addParameter("authorid", article.getAuthorId())
                    .executeUpdate()
                    .getKey();
            article.setId(id);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeArticle(int id)
    {
        String sqlStatement = "Delete from articles where id =:id";
        try(Connection con = DatabaseConnection.sql2o.open())
        {
            con.createQuery(sqlStatement)
                    .addParameter("id",id)
                    .executeUpdate();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public Article getSpecificArticle(int id) {

        String sqlStatement = "Select * from articles where id=:id";
        try(Connection con = DatabaseConnection.sql2o.open())
        {
          return  con.createQuery(sqlStatement)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Article.class);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Article> searchArticle(String search) {
        String sqlStatement = "Select * from articles";
        try(Connection con = DatabaseConnection.sql2o.open())
        {
          return  con.createQuery(sqlStatement)
                    .executeAndFetch(Article.class)
                  .stream()
                  .filter(article -> article.getHeading().toLowerCase()
                          .contains(search.toLowerCase())||article.getAuthor().toLowerCase()
                          .contains(search.toLowerCase())).collect(Collectors.toList());

        }
        catch (Exception e)
        {
e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Article> getAllArticles() {
        String sqlStatement = "Select * from articles";
        try(Connection con = DatabaseConnection.sql2o.open())
        {
            return  con.createQuery(sqlStatement)
                    .executeAndFetch(Article.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void editArticle(Article article) {

        String sqlStatement = "Update articles set (heading,articlecontent,author,authorid) = ( :heading, :articlecontent, :author,:authorid) where id=:id;";
        try (Connection conn = DatabaseConnection.sql2o.open()) {
            int id = (int) conn.createQuery(sqlStatement, true)
                    .addParameter("heading", article.getHeading())
                    .addParameter("articlecontent", article.getArticleContent())
                    .addParameter("author", article.getAuthor())
                    .addParameter("authorid", article.getAuthorId())
                    .addParameter("id",article.getId())
                    .executeUpdate()
                    .getKey();
            article.setId(id);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
