You will need to design and create your own Service classes from scratch.
You should refer to prior mini-project lab examples and course material for guidance.

package Service;

import DAO.AccountDAO;
import DAO.MessageDAO;
import DAO;

import Model.Account;
import Model.Message;

import java.util.List;


public class AccountService {
    private final AccountDAO accountDAO;

    piblic AccountService() {
        this.accountDAO = new AccountDAO();
    }

    public Account registerAccount(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null || password.length() < 4) {
            return null;
        }

        Account newAccount = new Account(username, password);
        return accountDAO.createAccount(newAccount);
    }

    public Account loginAccount(String username, String password) {
        return accountDAO.getAccountByUsernameAndPassword(username, password);
    }

    public Account getAccountById(int accountId) {
        return accountDAO.getAccountById(accountId);
    }

}


public class MessageService {

    private final MessageDAO messageDAO;

    public MessageService() {
        this.messageDAO = new MessageDAO();
    }


    public Message createMessage(int postedBy, String messageText, long timePostedEpoch) {
        if (messageText == null || messageText.trim().isEmpty() || messageText.length() > 255) {
            return null;
        }

        AccountService accountService = new AccountService();
        if (accountService.getAccountById(postedBy) == null) {
            return null;
        }

        Message newMessage = new Message(postedBy, messageText, timePostedEpoch);
        return messageDAO.createMessage(newMessage);
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }


    public List<Message> getMessageByUser(int userId) {
        return messageDAO.getMessageByUser(userId);
    }

    public Message updateMessage(int messageId, String newMessageText) {
        if (newMessageText == null || newMessageText.trim().isEmpty() || newMessageText.length() > 255) {
            return null;
        }

        return messageDAO.updateMessage(messageId, newMessageText);
    }

    public Message deleteMessage(int messageId) {
        return messageDAO.deleteMessage(messageId);
    }
}