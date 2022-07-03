import com.google.gson.Gson;
import daos.ArticlesSql2oDao;
import daos.ChatSql2oDao;
import models.Article;
import models.Chat;


import java.util.List;

import static spark.Spark.*;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.route.HttpMethod.get;
import static spark.route.HttpMethod.post;

public class App {
    public static void main(String[] args) {
        Gson gson = new Gson();
///////////////////////////////////////////////ARTICLE//////////////////////////////////////////////////////////////
        ArticlesSql2oDao ArticlesSql2oDao = new ArticlesSql2oDao();
        get("/get-allArticles",(req, res) ->{
            List<Article> list = ArticlesSql2oDao.getAllArticles();
            return gson.toJson(list);
        });
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
/////////////////////////////////////////CHATS/////////////////////////////////////////////////
        ChatSql2oDao chatDao = new ChatSql2oDao();
        get("/get-allChats",(req, res) ->{
            List<Chat> list = chatDao.getAllChats();
           return gson.toJson(list);
        });
        post("/chat",(req, res) ->{
            Chat chat = new Chat(15,14,"wertyrtyui");
            chatDao.addArticle(chat);
            return gson.toJson(chat);
        });

        patch("/edit-chat",(req, res) ->{
            Chat chat = new Chat(1,3,"any message");
            chat.setID(1);
            chatDao.editArticle(chat);
            return gson.toJson(chat);
        });


        delete("/delete-chat", (req, res) -> {
            chatDao.removeArticle(9);
            responseObject OBJ = new responseObject("Success deleted", "Ok", 200);
            return gson.toJson(OBJ);
        });
        get("/search-chat/:search", (req, res) -> {
            String query = req.params("search");
            List<Chat> list = chatDao.searchChats(query);
            System.out.println(list.size());
            return gson.toJson(list);
        });
        get("/chat-thread",(req,res)->{
            List<Chat> list = chatDao.getSpecificChatThread(1,3);
            return gson.toJson(list);
        });






    }

    private static class responseObject {
        String message ;
        String status;
        int statuscode;

        public responseObject(String message, String status, int statuscode) {
            this.message = message;
            this.status = status;
            this.statuscode = statuscode;
        }
    }
}
