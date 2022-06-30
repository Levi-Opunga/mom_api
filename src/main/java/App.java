import com.google.gson.Gson;
import daos.ArticlesSql2oDao;
import models.Article;


import java.util.List;

import static spark.Spark.*;
import static spark.Spark.delete;
import static spark.Spark.get;

public class App {
    public static void main(String[] args) {



        Gson gson = new Gson();
        ArticlesSql2oDao ArticlesSql2oDao = new ArticlesSql2oDao();

        post("/post-article", (request, response) -> {
            Article article = gson.fromJson(request.body(), Article.class);
            ArticlesSql2oDao.addArticle(article);
            return gson.toJson(article);
        });

        delete("/delete-article", (req, res) -> {
            ArticlesSql2oDao.removeArticle(4);
            responseObject OBJ = new responseObject("Success deleted", "Ok", 200);
            return gson.toJson(OBJ);
        });

        get("/search-articles/:search", (req, res) -> {
            String query = req.params("search");
            List<Article> list = ArticlesSql2oDao.searchArticle(query);
            System.out.println(list.size());
            return gson.toJson(list);
        });

        get("/get-one-article/:search", (req, res) -> {
            int query = Integer.parseInt(req.params("search"));
           Article article = ArticlesSql2oDao.getSpecificArticle(query);

            return gson.toJson(article);
        });

        patch("/update-article",(req, res) ->{
          //  Article article1 = gson.fromJson(req.body(),Article.class);
            Article article = new Article("tyuifghj",236666,"ertyuiopdfghjkl;0fguio","tyuifghjk");
            article.setId(6);
            ArticlesSql2oDao.editArticle(article);

            return gson.toJson(article);
        });


    }

    private static class responseObject {
        public responseObject(String success, String ok, int i) {
        }
    }
}
