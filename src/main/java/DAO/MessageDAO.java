// package DAO;

// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// import Util.ConnectionUtil;

// public class MessageDAO {

//     public Message createMessage(Message message) {

//         String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
//         Connection connection = null;

//         try {
//             Connection con = ConnectionUtil.getConnection();
//             preparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//             ps.setInt(1, message.getPosted_by());
//             ps.setString(2, message.getMessage_text());
//             ps.setLong(3, message.getTime_posted_epoch())
//             ps.executeUpdate();


//             ResultSet rs = ps.getGeneratedKeys();

//             if (rs.next()) {
//                 message.setMessage_id(rs.getInt(1));
//                 return message;
//             }

//         } catch (SQLException e) {
//             e.printStackTrace();
//         } finally {
//             if (connection != null) {
//                 try {
//                     connection.close();
//                 } catch (SQLException e) {
//                     e.printStackTrace();
//                 }
//             }
//         }
//         return null;
//     }

//     public List<Message> getAllMessages() {
//         String sql = "SELECT * FROM message";
//         List<Message> messages = new ArrayList<>();
//         Connection connection = null;

//         try {
//             Connection con = ConnectionUtil.getConnection();
//             preparedStatement ps = connection.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery();

//             while (rs.next()) {
//                 messages.add(new Message(
//                     rs.getInt("message_id"),
//                     rs.setInt("posted_by"),
//                     rs.getString("message_text"),
//                     rs.setLong("time_posted_epoch")));
//             }  

//         } catch (SQLException e) {
//             e.printStackTrace();
//         } finally {
//             if (connection != null) {
//                 try {
//                     connection.close();
//                 } catch (SQLException e) {
//                     e.printStackTrace();
//                 }
//             }
//         }
//         return messages;
//     }


//     public List<Message> getMessagesByUser(int userId) {
//         String sql = "SELECT * FROM message WHERE posted_by = ?";
//         List<Message> messages = new ArrayList<>();
//         Connection connection = null;

//         try {
//             Connection con = ConnectionUtil.getConnection();
//             preparedStatement ps = connection.prepareStatement(sql);
//             ps.setInt(1, userId);


//             ResultSet rs = ps.executeQuery();

//             while (rs.next()) {

//                 messages.add(new Message(
//                     rs.getInt("message_id"),
//                     rs.getInt("posted_by"),
//                     rs.getString("message_text"),
//                     rs.getLong("time_posted_epoch")));
//             }  

//         } catch (SQLException e) {
//             e.printStackTrace();
//         } finally {
//             if (connection != null) {
//                 try {
//                     connection.close();
//                 } catch (SQLException e) {
//                     e.printStackTrace();
//                 }
//             }
//         }
//         return messages;
//     }


//     public Message updateMessage(int messageId, String newMessageText) {
//         String sql = "UPDATE FROM message SET message_text = ? WHERE message_id = ?";
//         Connection connection = null;

//         try {
//             Connection con = ConnectionUtil.getConnection();
//             preparedStatement ps = connection.prepareStatement(sql);

//             ps.setString(1, newMessageText);
//             ps.setInt(2, messageId);

//             ps.executeUpdate();

//             return getMessageById(messageId);

//         } catch (SQLException e) {
//             e.printStackTrace();
//         } finally {
//             if (connection != null) {
//                 try {
//                     connection.close();
//                 } catch (SQLException e) {
//                     e.printStackTrace();
//                 }
//             }
//         }
//         return null;
//     }

//     public Message deleteMessage(int messageId) {
//         String sql = "DELETE FROM message WHERE message_id = ?";
//         Connection connection = null;

//         try {
//             Connection con = ConnectionUtil.getConnection();
//             preparedStatement ps = connection.prepareStatement(sql);

//             ps.setInt(1, messageId);

//             ps.executeUpdate();

//             return new Message(messageId, 0, null, 0);

//         } catch (SQLException e) {
//             e.printStackTrace();
//         } finally {
//             if (connection != null) {
//                 try {
//                     connection.close();
//                 } catch (SQLException e) {
//                     e.printStackTrace();
//                 }
//             }
//         }
//         return null;
//     }



//     public Message getMessageById(int messageId) {
//         String sql = "SELECT * FROM message WHERE posted_by = ?";
//         Connection connection = null;

//         try {
//             Connection con = ConnectionUtil.getConnection();
//             preparedStatement ps = connection.prepareStatement(sql);
//             ps.setInt(1, userId);


//             ResultSet rs = ps.executeQuery();

//             if (rs.next()) {

//                 return new Message(
//                     rs.getInt("message_id"),
//                     rs.setInt("posted_by"),
//                     rs.getString("message_text"),
//                     rs.getLong("time_posted_epoch"));
//             }  

//         } catch (SQLException e) {
//             e.printStackTrace();
//         } finally {
//             if (connection != null) {
//                 try {
//                     connection.close();
//                 } catch (SQLException e) {
//                     e.printStackTrace();
//                 }
//             }
//         }
//         return null;
//     }
// }

// }
