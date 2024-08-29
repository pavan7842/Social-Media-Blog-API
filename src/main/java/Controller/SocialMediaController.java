package Controller;

import Model.Message;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import io.javalin.Javalin;
import io.javalin.http.Context;

import Service.AccountService;
import Service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;


    public SocialMediaController() {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);

        app.post("/register", this::registerAccountHandler);

        app.post("/login", this::loginHandler);

        app.post("/messages", this::createMessageHandler);

        app.get("/messages", this::getAllMessagesHandler);

        app.get("/messages/{message_id}", this::getMessageByIdHandler);

        app.delete("/messages/{message_id}", this::deleteMessageHandler);

        app.patch("/messages/{message_id}", this::updateMessageHandler);

        app.get("/accounts/{account_id}/messages", this::getMessagesByUserHandler);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");

    }

    private void registerAccountHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(),Account.class);
        Account registeredAccount = accountService.registerAccount(account.getUsername(), account.getPassword());
    
        if (registeredAccount != null) {
            context.json(registeredAccount).status(200);
        } else {
            context.status(400);
        }
    }    

    private void loginHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(),Account.class);
        Account loggedInAccount = accountService.loginAccount(account.getUsername(), account.getPassword());

        if (loggedInAccount != null) {
            context.json(loggedInAccount).status(200);
        } else {
            context.status(401);
        }
    }

    private void createMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message createdMessage = messageService.createMessage(message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
        
        if (createdMessage != null) {
            context.json(createdMessage).status(200);
        } else {
            context.status(400);
        }
    }

    private void getAllMessagesHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Message> messages = messageService.getAllMessages();
        context.json(mapper.writeValueAsString(messages)).status(200);
    }

    private void getMessageByIdHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int messageId = Integer.parseInt(context.pathParam("message_id"));
        Message getMessage = messageService.getMessageById(messageId);

        if (getMessage != null) {
            context.json(mapper.writeValueAsString(getMessage)).status(200);
        } else {
            context.status(200).result("");
        }
    }

    private void deleteMessageHandler(Context context) throws JsonProcessingException {
        int messageId = Integer.parseInt(context.pathParam("message_id"));
        Message deletedMessage = messageService.deleteMessage(messageId);

        if (deletedMessage != null) {
            context.json(deletedMessage).status(200);
        } else {
            context.status(200).result("");
        }
    }

    private void updateMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int messageId = Integer.parseInt(context.pathParam("message_id"));
        String newMessageText = mapper.readValue(context.body(), Message.class).getMessage_text();
        Message updatedMessage = messageService.updateMessage(messageId, newMessageText);

        if (updatedMessage != null) {
            context.json(mapper.writeValueAsString(updatedMessage)).status(200);
        } else {
            context.status(400);
        }
    }

    private void getMessagesByUserHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int accountId = Integer.parseInt(context.pathParam("account_id"));
        List<Message> messages = messageService.getMessageByUser(accountId);
        context.json(mapper.writeValueAsString(messages)).status(200);
    }
}