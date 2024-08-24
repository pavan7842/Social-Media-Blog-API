package Controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import io.javalin.Javalin;
import io.javalin.http.Context;
import Service.AccountService;
// import Service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    AccountService accountService;
    // MessageService messageService;


    public SocialMediaController() {
        this.accountService = new AccountService();
        // this.messageService = new MessageService();
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

        // app.post("/message", this::createMessageHandler);

        // app.get("/message", this::getAllMessagesHandler);

        // app.get("/message/{message_id}", this::getMessageByIdHandler);

        // app.delete("/message/{message_id}", this::deleteMessageHandler);

        // app.patch("/message/{message_id}", this::updateMessageHandler);

        // app.get("/accounts/{account_id}/messages", this::getMessagesByUserHandler);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");

    }

    private void registerAccountHandler(Context ctx) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        Account account = mapper.readValue(ctx.body(),Account.class);

        Account registeredAccount = accountService.registerAccount(account.getUsername(), account.getPassword());
        
        if (registeredAccount != null) {
            ctx.json(registeredAccount).status(200);
        } else {
            ctx.status(400);
        }
    }    

    private void loginHandler(Context ctx) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        Account account = mapper.readValue(ctx.body(),Account.class);

        Account loggedInAccount = accountService.loginAccount(account.getUsername(), account.getPassword());

        if (loggedInAccount != null) {
            ctx.json(loggedInAccount).status(200);
        } else {
            ctx.status(401);
        }
    }

    // private void createMessageHandler(Context context) {
    //     Message message = context.bodyAsClass(Message.class);

    //     Message createdMessage = socialMediaService.createMessage(message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
        
    //     if (createdMessage != null) {
    //         context.json(createdMessage).status(200);
    //     } else {
    //         context.status(400);
    //     }
    // }

    // private void getAllMessagesHandler(Context context) {
    //     context.json("retrieve all messages");
    // }

    // private void getMessageByIdHandler(Context context) {
    //     context.json("retrieve message by ID");  
    // }

    // private void deleteMessageHandler(Context context) {
    //     context.json("delete message by ID");    
    // }

    // private void updateMessageHandler(Context context) {
    //     context.json("update message by ID");
    // }

    // private void getMessagesByUserHandler(Context context) {
    //     context.json("retrieve all messages by user ID");
    // }

}