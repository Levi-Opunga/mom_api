package daos;

import models.Article;
import models.Chat;
import org.sql2o.Connection;

import java.util.List;
import java.util.stream.Collectors;

public class ChatSql2oDao implements ChatDao {
    @Override
    public void addArticle(Chat chat) {
        String sqlStatement = "Insert into chats (message,senderid, receiverid) values( :message, :senderid, :receiverid);";
        try (Connection conn = DatabaseConnection.sql2o.open()) {
            int id = (int) conn.createQuery(sqlStatement, true)
                    .addParameter("message", chat.getMessage())
                    .addParameter("receiverid", chat.getReceiverID())
                    .addParameter("senderid", chat.getSenderID())
                    .executeUpdate()
                    .getKey();
            chat.setID(id);
        }catch (Exception e){
e.printStackTrace();
        }

    }

    @Override
    public void removeArticle(int id) {
        String sqlStatement = "Delete from chats where id =:id";
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
    public List<Chat> getSpecificChatThread(int senderID,int receiverID) {
        String sqlStatement = "Select * from chats where receiverid=:receiverid AND senderid=:senderid OR senderid=:receiverid AND receiverid=:senderid;";
        try(Connection con = DatabaseConnection.sql2o.open())
        {
            return  con.createQuery(sqlStatement)
                    .addParameter("senderid",senderID)
                    .addParameter("receiverid",receiverID)
                    .executeAndFetch(Chat.class);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Chat> searchChats(String search) {
        String sqlStatement = "Select * from chats";
        try(Connection con = DatabaseConnection.sql2o.open())
        {
            return  con.createQuery(sqlStatement)
                    .executeAndFetch(Chat.class)
                    .stream()
                    .filter(chat -> chat.getMessage().toLowerCase()
                            .contains(search.toLowerCase())).collect(Collectors.toList());

        }catch (Exception e) {
e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Chat> getAllChats() {
        String sqlStatement = "Select * from chats";
        try(Connection con = DatabaseConnection.sql2o.open())
        {
            return  con.createQuery(sqlStatement)
                    .executeAndFetch(Chat.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void editChat(Chat chat) {

        String sqlStatement = "Update chats set (message,senderid, receiverid) = ( :message, :senderid, :receiverid) where id=:id;";
        try (Connection conn = DatabaseConnection.sql2o.open()) {
            int id = (int) conn.createQuery(sqlStatement, true)
                    .addParameter("message", chat.getMessage())
                    .addParameter("receiverid", chat.getReceiverID())
                    .addParameter("senderid", chat.getSenderID())
                    .addParameter("id",chat.getID())
                    .executeUpdate()
                    .getKey();
            chat.setID(id);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
