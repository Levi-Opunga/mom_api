import com.google.gson.Gson;
import daos.ArticlesSql2oDao;
import daos.ChatSql2oDao;
import daos.UserSql20Dao;
import models.Article;
import models.Chat;
import models.User;


import java.util.List;

import static spark.Spark.*;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.route.HttpMethod.get;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        Gson gson = new Gson();
        responseObject OBJ = new responseObject("Success deleted", "Deleted Succesfully", 200);

///////////////////////////////////////////////ARTICLE//////////////////////////////////////////////////////////////
        ArticlesSql2oDao ArticlesSql2oDao = new ArticlesSql2oDao();
        get("/get-allArticles", (req, res) -> {
            List<Article> list = ArticlesSql2oDao.getAllArticles();
            return gson.toJson(list);
        });
        post("/post-article", (request, response) -> {
            Article article = gson.fromJson(request.body(), Article.class);
            ArticlesSql2oDao.addArticle(article);
            return gson.toJson(article);
        });

        delete("/delete-article/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            ArticlesSql2oDao.removeArticle(id);
            return gson.toJson(OBJ);
        });

        get("/search-articles/:search", (req, res) -> {
            String query = req.params("search");
            List<Article> list = ArticlesSql2oDao.searchArticle(query);
            System.out.println(list.size());
            return gson.toJson(list);
        });

        get("/get-one-article/:id", (req, res) -> {
            int query = Integer.parseInt(req.params("id"));
            Article article = ArticlesSql2oDao.getSpecificArticle(query);

            return gson.toJson(article);
        });

        patch("/update-article/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            Article oldArticle = ArticlesSql2oDao.getSpecificArticle(id);
            Article article = gson.fromJson(req.body(), Article.class);
            if (!oldArticle.equals(article)) {
                ArticlesSql2oDao.editArticle(oldArticle);
                return oldArticle;
            }
            article.setId(6);
            ArticlesSql2oDao.editArticle(article);

            return gson.toJson(article);
        });
/////////////////////////////////////////CHATS/////////////////////////////////////////////////
        ChatSql2oDao chatDao = new ChatSql2oDao();
        get("/get-allChats", (req, res) -> {
            List<Chat> list = chatDao.getAllChats();
            return gson.toJson(list);
        });
        post("/chat", (req, res) -> {
            Chat  chat = gson.fromJson(req.body(), Chat.class);
            chatDao.addArticle(chat);
            return gson.toJson(chat);
        });

//
//        patch("/edit-chat/:id", (req, res) -> {
//            int id = Integer.parseInt(req.params("id"));
//            Chat chat = chatDao.g(id);
//            chat.setID(1);
//            chatDao.editArticle(chat);
//            return gson.toJson(chat);
//        });


        delete("/delete-chat/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            chatDao.removeArticle(id);
            return gson.toJson(OBJ);
        });
        get("/search-chat/:search", (req, res) -> {
            String query = req.params("search");
            List<Chat> list = chatDao.searchChats(query);
            System.out.println(list.size());
            return gson.toJson(list);
        });
        get("/chat-thread/:one/:two", (req, res) -> {
            int one = Integer.parseInt(req.params("one"));
            int two = Integer.parseInt(req.params("two"));
            List<Chat> list = chatDao.getSpecificChatThread(one, two);
            return gson.toJson(list);
        });
        ////////////////////////////////////////////User/////////////////////////////////////////////
        UserSql20Dao userSql20Dao = new UserSql20Dao();


        post("/post-user", "application/json", (request, response) -> {
            User user = gson.fromJson(request.body(), User.class);
            userSql20Dao.addUser(user);
            return gson.toJson(user);
        });

        delete("/delete-user/:id", (request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            userSql20Dao.removeUser(id);
            return gson.toJson(OBJ);
        });
        get("/search-user/:search", (request, response) -> {
            String query = request.params("search");
            List<User> list = userSql20Dao.searchUser(query);
            System.out.println(list.size());
            return gson.toJson(list);
        });

        patch("/update-user/:id", (req, res) -> {

            User edited = gson.fromJson(req.body(), User.class);
            User user = userSql20Dao.getSpecificUser(Integer.parseInt(req.params("id")));
            if (!user.equals(edited)) {
//                user.setName(edited.getName());
//                user.setCountry(edited.getCountry());
//                user.setImageId(edited.getImageId());
//                user.setLastMessage(edited.getLastMessage());
//                user.setLastMsgTime(edited.getLastMsgTime());
//                user.setPhoneNo(edited.getPhoneNo());
//                userSql20Dao.editUser(user);
                userSql20Dao.editUser(edited);
                return gson.toJson(edited);
            }

            return gson.toJson(user);
        });

        get("/get-oneUser/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
          User user =  userSql20Dao.getSpecificUser(id);
            return gson.toJson(user);
        });


    }

    private static class responseObject {
        String message;
        String status;
        int statusCode;

        public responseObject(String message, String status, int statusCode) {
            this.message = message;
            this.status = status;
            this.statusCode = statusCode;
        }
    }
}
