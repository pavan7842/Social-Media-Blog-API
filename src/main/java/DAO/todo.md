You will need to design and create your own DAO classes from scratch. 
You should refer to prior mini-project lab examples and course material for guidance.

Please refrain from using a 'try-with-resources' block when connecting to your database. 
The ConnectionUtil provided uses a singleton, and using a try-with-resources will cause issues in the tests.

package DAO;

import Model.Account;
import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AccountDAO {

    public Account createAccount(Account account) {

        String sql = "INSERT INTO account (username, password) VALUES (?, ?)";
        Connection connection = null;

        try {
            Connection con = ConnectionUtil.getConnection();
        
            preparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                account.setAccount_id(rs.getInt(1));
                return account;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
        
    }

    public Account getAccountByUsernameAndPassword(String username, String password) {

        String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
        Connection connection = null;

        try {
            Connection con = ConnectionUtil.getConnection();
            preparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
        
    }

    public Account getAccountById(int accountId) {
        String sql = "SELECT * FROM account WHERE account_id = ?";
        Connection connection = null;

        try {
            Connection con = ConnectionUtil.getConnection();
            preparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

}


public class MessageDAO {

    public Message createMessage(Message message) {

        String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
        Connection connection = null;

        try {
            Connection con = ConnectionUtil.getConnection();
            preparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch())
            ps.executeUpdate();


            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                message.setMessage_id(rs.getInt(1));
                return message;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public List<Message> getAllMessages() {
        String sql = "SELECT * FROM message";
        List<Message> messages = new ArrayList<>();
        Connection connection = null;

        try {
            Connection con = ConnectionUtil.getConnection();
            preparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                messages.add(new Message(
                    rs.getInt("message_id"),
                    rs.setInt("posted_by"),
                    rs.getString("message_text"),
                    rs.setLong("time_posted_epoch")));
            }  

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return messages;
    }


    public List<Message> getMessagesByUser(int userId) {
        String sql = "SELECT * FROM message WHERE posted_by = ?";
        List<Message> messages = new ArrayList<>();
        Connection connection = null;

        try {
            Connection con = ConnectionUtil.getConnection();
            preparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                messages.add(new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch")));
            }  

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return messages;
    }


    public Message updateMessage(int messageId, String newMessageText) {
        String sql = "UPDATE FROM message SET message_text = ? WHERE message_id = ?";
        Connection connection = null;

        try {
            Connection con = ConnectionUtil.getConnection();
            preparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, newMessageText);
            ps.setInt(2, messageId);

            ps.executeUpdate();

            return getMessageById(messageId);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public Message deleteMessage(int messageId) {
        String sql = "DELETE FROM message WHERE message_id = ?";
        Connection connection = null;

        try {
            Connection con = ConnectionUtil.getConnection();
            preparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, messageId);

            ps.executeUpdate();

            return new Message(messageId, 0, null, 0);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }



    public Message getMessageById(int messageId) {
        String sql = "SELECT * FROM message WHERE posted_by = ?";
        Connection connection = null;

        try {
            Connection con = ConnectionUtil.getConnection();
            preparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);


            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Message(
                    rs.getInt("message_id"),
                    rs.setInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch"));
            }  

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}

