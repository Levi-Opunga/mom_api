package daos;

import models.Article;
import models.Chat;

import java.util.List;

public interface ChatDao
{
    void addArticle(Chat chat);

    void removeArticle(int id);

   List<Chat> getSpecificChatThread(int senderID,int ReceiverID);

    List<Chat> searchChats(String search);
    List<Chat> getAllChats();
    void editChat(Chat chat);
}
