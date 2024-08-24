package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
   
    private AccountDAO accountDAO;

    public AccountService() {
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
