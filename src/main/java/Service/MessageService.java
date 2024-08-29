package Service;

import java.util.List;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Account;
import Model.Message;

public class MessageService {

    private final MessageDAO messageDAO;
    private final AccountDAO accountDAO;

    public MessageService() {
        this.messageDAO = new MessageDAO();
        this.accountDAO = new AccountDAO();
    }

    public Message createMessage(int postedBy, String messageText, long timePostedEpoch) {
        if (messageText == null || messageText.trim().isEmpty() || messageText.length() > 255) {
            return null;
        }

        Account account = accountDAO.getAccountById(postedBy);
        if (account == null) {
            return null;
        }

        Message newMessage = new Message(postedBy, messageText, timePostedEpoch);
        return messageDAO.createMessage(newMessage);
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int messageId) {
        return messageDAO.getMessageById(messageId);
    }

    public Message deleteMessage(int messageId) {

        Message messageToDelete = messageDAO.getMessageById(messageId);

        if (messageToDelete != null) {
            boolean isDeleted = messageDAO.deleteMessage(messageId);
            if (isDeleted) {
                return messageToDelete;
            }
        }
        return null;
     }

    public Message updateMessage(int messageId, String newMessageText) {
        if (newMessageText == null || newMessageText.trim().isEmpty() || newMessageText.length() > 255) {
            return null;
        }

        Message existingMessage = messageDAO.getMessageById(messageId);
        if (existingMessage != null) {
            return messageDAO.updateMessage(messageId, newMessageText);
        }
        return null;
     }

    public List<Message> getMessageByUser(int userId) {
        return messageDAO.getMessagesByUser(userId);
    }
    
}