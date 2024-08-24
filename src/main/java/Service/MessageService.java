// package Service;

// import java.util.List;

// import DAO.MessageDAO;

// public class MessageService {

//     private final MessageDAO messageDAO;

//     public MessageService() {
//         this.messageDAO = new MessageDAO();
//     }


//     public Message createMessage(int postedBy, String messageText, long timePostedEpoch) {
//         if (messageText == null || messageText.trim().isEmpty() || messageText.length() > 255) {
//             return null;
//         }

//         AccountService accountService = new AccountService();
//         if (accountService.getAccountById(postedBy) == null) {
//             return null;
//         }

//         Message newMessage = new Message(postedBy, messageText, timePostedEpoch);
//         return messageDAO.createMessage(newMessage);
//     }

//     public List<Message> getAllMessages() {
//         return messageDAO.getAllMessages();
//     }


//     public List<Message> getMessageByUser(int userId) {
//         return messageDAO.getMessageByUser(userId);
//     }

//     public Message updateMessage(int messageId, String newMessageText) {
//         if (newMessageText == null || newMessageText.trim().isEmpty() || newMessageText.length() > 255) {
//             return null;
//         }

//         return messageDAO.updateMessage(messageId, newMessageText);
//     }

//     public Message deleteMessage(int messageId) {
//         return messageDAO.deleteMessage(messageId);
//     }
// }

// }
