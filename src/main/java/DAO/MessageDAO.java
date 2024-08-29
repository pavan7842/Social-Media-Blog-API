package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {

    public Message createMessage(Message message) {
        Connection con = ConnectionUtil.getConnection();
        String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                message.setMessage_id(rs.getInt(1));
                return message;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return null;
    }

    public List<Message> getAllMessages() {

        List<Message> messages = new ArrayList<>();
        Connection con = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM message";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
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
        } 
        return messages;
    }

    public Message getMessageById(int messageId) {
        Connection con = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM message WHERE message_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, messageId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch")
                );
            }  
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return null;
    }


    public boolean deleteMessage(int messageId) {
        Connection con = ConnectionUtil.getConnection();
        String sql = "DELETE FROM message WHERE message_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, messageId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return false;
    }


    public Message updateMessage(int messageId, String newMessageText) {
        Connection con = ConnectionUtil.getConnection();
        String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newMessageText);
            ps.setInt(2, messageId);
            ps.executeUpdate();
            return getMessageById(messageId);
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return null;
    }

    public List<Message> getMessagesByUser(int userId) {
        Connection con = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM message WHERE posted_by = ?";
        List<Message> messages = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
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
        } 
        return messages;
    }
}