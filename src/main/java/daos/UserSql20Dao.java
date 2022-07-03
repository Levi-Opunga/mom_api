package daos;

import models.User;

import org.sql2o.Connection;
import java.util.List;
import java.util.stream.Collectors;


public class UserSql20Dao implements UsersDao {
    @Override
    public void addUser(User user) {
        String sqlStatement = "INSERT into users(name, lastmessage, lastmsgtime, phoneno,conutry, imagid) values(:name, :lastmessage, :lastmsgtime, :phoneno, :conutry, :imagid)";
        try(Connection conn = DatabaseConnection.sql2o.open()){
            int id = (int) conn.createQuery(sqlStatement, true)
                    .addParameter("name", user.getName())
                    .addParameter("lastmessage", user.getLastMessage())
                    .addParameter("lastmsgtime", user.getLastMsgTime())
                    .addParameter("phoneno", user.getPhoneNo())
                    .addParameter("conutry", user.getCountry())
                    .addParameter("imagid", user.getImageId())
                    .executeUpdate()
                    .getKey();
            user.setId(id);


        } catch (Exception e) {
            System.out.println(e);
        }


    }

    @Override
    public void removeUser(int id) {
        String sqlStatement = "DeELETE from users where id = :id";
        try(Connection conn = DatabaseConnection.sql2o.open())
        {
            conn.createQuery(sqlStatement)
                    .addParameter("id", id)
                    .executeUpdate();
        }
        catch (Exception e){
            System.out.println(e);
        }


    }

    @Override
    public User getSpecificUser(int id) {
        String sqlStatement = "SELECT from users where id = :id";
        try(Connection conn = DatabaseConnection.sql2o.open()){
            return conn.createQuery(sqlStatement)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<User> searchUser(String search) {
        String sqlStatement = "SELECT * from users";
        try(Connection conn = DatabaseConnection.sql2o.open()){
            return conn.createQuery(sqlStatement)
                    .executeAndFetch(User.class)
                    .stream()
                    .filter(user -> user.getName().toUpperCase()
                            .contains(search.toLowerCase()) || user.getPhoneNo().toUpperCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());

        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void editUser(User user) {
        String sqlStatement = "UPDATE users set (name, lastMessage, lastMsgTime, phoneNo,country, imageId) values(:name, :lastMessage, :lastMsgTime, :phoneNo,country, :imageId)";
        try (Connection conn = DatabaseConnection.sql2o.open()){
            int id = (int) conn.createQuery(sqlStatement, true)
                    .addParameter("name", user.getName())
                    .addParameter("lastMassage", user.getLastMessage())
                    .addParameter("lastMsgTime", user.getLastMsgTime())
                    .addParameter("phoneNo", user.getPhoneNo())
                    .addParameter("country", user.getCountry())
                    .addParameter("imageId", user.getImageId())
                    .executeUpdate()
                    .getKey();
            user.setId(id);

        }
        catch (Exception e){
            System.out.println(e);
        }


    }
}
