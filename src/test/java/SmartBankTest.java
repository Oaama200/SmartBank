import projects.first_topic.smart_bank_app.constant.ProjectConstant;
import projects.first_topic.smart_bank_app.factory.DAOFactory;
import projects.first_topic.smart_bank_app.model.*;
import projects.first_topic.smart_bank_app.services.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.sql.SQLException;

public class SmartBankTest {
    private static final User USER_1 = new User() {
        {
            setUser_name("JamesSmith");
            setUser_type("REWARD_USER");
            setPassword("password123");
            setFirst_name("James");
            setLast_name("Smith");
            setPhone("4748995503");
            setEmail("james.smith@gmail.com");
            setCredit_score(680);
            setAnnual_income(48000);
            setLoan_amount(30000);
            setRegistration_date("2010-02-01");
        }
    };

    private static final User USER_2 = new User() {
        {
            setUser_name("MaryJohnson");
            setUser_type("PLATINUM_USER");
            setPassword("password456");
            setFirst_name("Mary");
            setLast_name("Johnson");
            setPhone("6234213337");
            setEmail("mary.johnson@yahoo.com");
            setCredit_score(700);
            setAnnual_income(67000);
            setLoan_amount(50000);
            setRegistration_date("2011-06-10");
        }
    };

    private static final User USER_3 = new User() {
        {
            setUser_name("MichaelWilliams");
            setUser_type("PLATINUM_USER");
            setPassword("password789");
            setFirst_name("Michael");
            setLast_name("Williams");
            setPhone("3077018838");
            setEmail("michael.williams@gmail.com");
            setCredit_score(750);
            setAnnual_income(73000);
            setLoan_amount(23000);
            setRegistration_date("2012-08-30");
        }
    };


    private static final Account ACCOUNT_1 = new Account() {
        {
            setUser_id(1);
            setAccount_type("CHECKING_ACCOUNT");
            setBalance(6000);
        }
    };

    private static final Account ACCOUNT_2 = new Account() {
        {
            setUser_id(2);
            setAccount_type("CHECKING_ACCOUNT");
            setBalance(3000);
        }
    };

    private static final Account ACCOUNT_3 = new Account() {
        {
            setUser_id(2);
            setAccount_type("SAVINGS_ACCOUNT");
            setBalance(20000);
        }
    };

    private static final Account ACCOUNT_4 = new Account() {
        {
            setUser_id(3);
            setAccount_type("SAVINGS_ACCOUNT");
            setBalance(50000);
        }
    };



    private static final LoanApplication LOAN_APPLICATION_1 = new LoanApplication() {
        {
            setUser_id(1);
            setLoan_type("Auto Loan");
            setAmount(21000);
            setStart_date("2024-05-30");
            setEnd_date("2026-05-30");
            setApplication_status("pending");
            setApplication_date("2024-05-30");
        }
    };

    private static final LoanApplication LOAN_APPLICATION_2 = new LoanApplication() {
        {
            setUser_id(1);
            setLoan_type("Mortage");
            setAmount(200000);
            setStart_date("2024-09-01");
            setEnd_date("2034-09-01");
            setApplication_status("pending");
            setApplication_date("2024-09-01");
        }
    };

    private static final LoanApplication LOAN_APPLICATION_3 = new LoanApplication() {
        {
            setUser_id(2);
            setLoan_type("Mortage");
            setAmount(150000);
            setStart_date("2024-08-20");
            setEnd_date("2039-08-20");
            setApplication_status("pending");
            setApplication_date("2024-08-20");
        }
    };

    private static final LoanApplication LOAN_APPLICATION_4 = new LoanApplication() {
        {
            setUser_id(3);
            setLoan_type("Small Business Loan");
            setAmount(100000);
            setStart_date("2024-10-30");
            setEnd_date("2029-10-30");
            setApplication_status("pending");
            setApplication_date("2024-10-30");
        }
    };

    private static final Transaction TRANSACTION_1 = new Transaction() {
        {
            setAccount_id(1);
            setTransaction_type("deposit");
            setTransaction_amount(1900);
            setTransaction_date("2024-08-30");
        }
    };

    private static final Transaction TRANSACTION_2 = new Transaction() {
        {
            setAccount_id(2);
            setTransaction_type("withdrawal");
            setTransaction_amount(1500);
            setTransaction_date("2024-10-20");
        }
    };

    private static final Transaction TRANSACTION_3 = new Transaction() {
        {
            setAccount_id(4);
            setTransaction_type("deposit");
            setTransaction_amount(70);
            setTransaction_date("2024-06-30");
        }
    };

    private static final Transaction TRANSACTION_4 = new Transaction() {
        {
            setAccount_id(1);
            setTransaction_type("withdrawal");
            setTransaction_amount(200000);
            setTransaction_date("2024-07-20");
        }
    };


    // Start Testing:
    // Create/Insert of CRUD
    @Test (priority = 1, description = "Create a User1")
    public void createUserTest01() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        UserService userService = new UserService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        userService.setSafeUpdates(0);
        userService.deleteAllUsers();
        userService.setSafeUpdates(1);
        userService.resetAutoIncrement();
        userService.createUser(USER_1);
        User user = userService.getUser(USER_1.getUser_id());
        System.out.println("User successfully created: " + user.getUser_name());
        checkUser01(user);
    }

    private void checkUser01(User user) {
        Assert.assertEquals(user.getUser_name(), USER_1.getUser_name(), "User name must match");
        Assert.assertEquals(user.getPassword(), USER_1.getPassword(), "Password must match");
        Assert.assertEquals(user.getEmail(), USER_1.getEmail(), "Email must match");
    }


    @Test (priority = 2, description = "Create a User2")
    public void createUserTest02() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        UserService userService = new UserService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        userService.createUser(USER_2);
        User user = userService.getUser(USER_2.getUser_id());
        System.out.println("User successfully created: " + user.getUser_name());
        checkUser02(user);
    }

    private void checkUser02(User user) {
        Assert.assertEquals(user.getUser_name(), USER_2.getUser_name(), "User name must match");
        Assert.assertEquals(user.getPassword(), USER_2.getPassword(), "Password must match");
        Assert.assertEquals(user.getEmail(), USER_2.getEmail(), "Email must match");
    }

    @Test (priority = 3, description = "Create a User3")
    public void createUserTest03() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        UserService userService = new UserService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        userService.createUser(USER_3);
        User user = userService.getUser(USER_3.getUser_id());
        System.out.println("User successfully created: " + user.getUser_name());
        checkUser03(user);
    }

    private void checkUser03(User user) {
        Assert.assertEquals(user.getUser_name(), USER_3.getUser_name(), "User name must match");
        Assert.assertEquals(user.getPassword(), USER_3.getPassword(), "Password must match");
        Assert.assertEquals(user.getEmail(), USER_3.getEmail(), "Email must match");
    }

    @Test (priority = 4, description = "Create an Account1")
    public void createAccountTest04() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        AccountService accountService = new AccountService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        accountService.setSafeUpdates(0);
        accountService.deleteAllAccount();
        accountService.setSafeUpdates(1);
        accountService.resetAutoIncrement();
        accountService.createAccount(ACCOUNT_1);
        Account account = accountService.getAccountById(ACCOUNT_1.getAccount_id());
        System.out.println("Account successfully created: " + account.getAccount_id());
        checkAccount01(account);
    }

    private void checkAccount01(Account account) {
        Assert.assertEquals(account.getUser_id(), ACCOUNT_1.getUser_id(), "User id must match");
        Assert.assertEquals(account.getAccount_type(), ACCOUNT_1.getAccount_type(), "Account Type must match");
        Assert.assertEquals(account.getBalance(), ACCOUNT_1.getBalance(), "Balance must match");
    }

    @Test (priority = 5, description = "Create an Account2")
    public void createAccountTest05() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        AccountService accountService = new AccountService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        accountService.createAccount(ACCOUNT_2);
        Account account = accountService.getAccountById(ACCOUNT_2.getAccount_id());
        System.out.println("Account successfully created: " + account.getAccount_id());
        checkAccount02(account);
    }

    private void checkAccount02(Account account) {
        Assert.assertEquals(account.getUser_id(), ACCOUNT_2.getUser_id(), "User id must match");
        Assert.assertEquals(account.getAccount_type(), ACCOUNT_2.getAccount_type(), "Account Type must match");
        Assert.assertEquals(account.getBalance(), ACCOUNT_2.getBalance(), "Balance must match");
    }

    @Test (priority = 6, description = "Create an Account3")
    public void createAccountTest06() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        AccountService accountService = new AccountService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        accountService.createAccount(ACCOUNT_3);
        Account account = accountService.getAccountById(ACCOUNT_3.getAccount_id());
        System.out.println("Account successfully created: " + account.getAccount_id());
        checkAccount03(account);
    }

    private void checkAccount03(Account account) {
        Assert.assertEquals(account.getUser_id(), ACCOUNT_3.getUser_id(), "User id must match");
        Assert.assertEquals(account.getAccount_type(), ACCOUNT_3.getAccount_type(), "Account Type must match");
        Assert.assertEquals(account.getBalance(), ACCOUNT_3.getBalance(), "Balance must match");
    }

    @Test (priority = 7, description = "Create an Account4")
    public void createAccountTest07() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        AccountService accountService = new AccountService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        accountService.createAccount(ACCOUNT_4);
        Account account = accountService.getAccountById(ACCOUNT_4.getAccount_id());
        System.out.println("Account successfully created: " + account.getAccount_id());
        checkAccount04(account);
    }

    private void checkAccount04(Account account) {
        Assert.assertEquals(account.getUser_id(), ACCOUNT_4.getUser_id(), "User id must match");
        Assert.assertEquals(account.getAccount_type(), ACCOUNT_4.getAccount_type(), "Account Type must match");
        Assert.assertEquals(account.getBalance(), ACCOUNT_4.getBalance(), "Balance must match");
    }

    @Test (priority = 8, description = "Create a LoanApplication1")
    public void createLoanApplicationTest08() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        LoanApplicationService loanApplicationService = new LoanApplicationService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        loanApplicationService.setSafeUpdates(0);
        loanApplicationService.deleteAllLoanApplications();
        loanApplicationService.setSafeUpdates(1);
        loanApplicationService.resetAutoIncrement();
        loanApplicationService.createLoanApplication(LOAN_APPLICATION_1);
        LoanApplication loanApplication = loanApplicationService
                .getLoanApplication(LOAN_APPLICATION_1.getApplication_id());
        System.out.println("Loan Application successfully created: " + loanApplication.getApplication_id());
        checkLoanApplication01(loanApplication);
    }

    private void checkLoanApplication01(LoanApplication loanApplication) {
        Assert.assertEquals(loanApplication.getUser_id(), LOAN_APPLICATION_1.getUser_id(),
                "User id must match");
        Assert.assertEquals(loanApplication.getLoan_type(), LOAN_APPLICATION_1.getLoan_type(),
                "Loan Type must match");
        Assert.assertEquals(loanApplication.getAmount(), LOAN_APPLICATION_1.getAmount(),
                "Amount must match");
    }

    @Test (priority = 9, description = "Create a LoanApplication2")
    public void createLoanApplicationTest09() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        LoanApplicationService loanApplicationService = new LoanApplicationService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        loanApplicationService.createLoanApplication(LOAN_APPLICATION_2);
        LoanApplication loanApplication = loanApplicationService
                .getLoanApplication(LOAN_APPLICATION_2.getApplication_id());
        System.out.println("Loan Application successfully created: " + loanApplication.getApplication_id());
        checkLoanApplication02(loanApplication);
    }

    private void checkLoanApplication02(LoanApplication loanApplication) {
        Assert.assertEquals(loanApplication.getUser_id(), LOAN_APPLICATION_2.getUser_id(),
                "User id must match");
        Assert.assertEquals(loanApplication.getLoan_type(), LOAN_APPLICATION_2.getLoan_type(),
                "Loan Type must match");
        Assert.assertEquals(loanApplication.getAmount(), LOAN_APPLICATION_2.getAmount(),
                "Amount must match");
    }

    @Test (priority = 10, description = "Create a LoanApplication3")
    public void createLoanApplicationTest10() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        LoanApplicationService loanApplicationService = new LoanApplicationService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        loanApplicationService.createLoanApplication(LOAN_APPLICATION_3);
        LoanApplication loanApplication = loanApplicationService
                .getLoanApplication(LOAN_APPLICATION_3.getApplication_id());
        System.out.println("Loan Application successfully created: " + loanApplication.getApplication_id());
        checkLoanApplication03(loanApplication);
    }

    private void checkLoanApplication03(LoanApplication loanApplication) {
        Assert.assertEquals(loanApplication.getUser_id(), LOAN_APPLICATION_3.getUser_id(),
                "User id must match");
        Assert.assertEquals(loanApplication.getLoan_type(), LOAN_APPLICATION_3.getLoan_type(),
                "Loan Type must match");
        Assert.assertEquals(loanApplication.getAmount(), LOAN_APPLICATION_3.getAmount(),
                "Amount must match");
    }

    @Test (priority = 11, description = "Create a LoanApplication4")
    public void createLoanApplicationTest11() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        LoanApplicationService loanApplicationService = new LoanApplicationService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        loanApplicationService.createLoanApplication(LOAN_APPLICATION_4);
        LoanApplication loanApplication = loanApplicationService
                .getLoanApplication(LOAN_APPLICATION_4.getApplication_id());
        System.out.println("Loan Application successfully created: " + loanApplication.getApplication_id());
        checkLoanApplication04(loanApplication);
    }

    private void checkLoanApplication04(LoanApplication loanApplication) {
        Assert.assertEquals(loanApplication.getUser_id(), LOAN_APPLICATION_4.getUser_id(),
                "User id must match");
        Assert.assertEquals(loanApplication.getLoan_type(), LOAN_APPLICATION_4.getLoan_type(),
                "Loan Type must match");
        Assert.assertEquals(loanApplication.getAmount(), LOAN_APPLICATION_4.getAmount(),
                "Amount must match");
    }

    @Test (priority = 12, description = "Create a Transaction1")
    public void createTransactionTest12() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        TransactionService transactionService = new TransactionService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        transactionService.setSafeUpdates(0);
        transactionService.deleteAllTransactions();
        transactionService.setSafeUpdates(1);
        transactionService.resetAutoIncrement();
        transactionService.createTransaction(TRANSACTION_1);
        Transaction transaction = transactionService.getTransaction(TRANSACTION_1.getTransaction_id());
        System.out.println("Transaction successfully created: " + transaction.getTransaction_id());
//        System.out.println("Account_balance_before: " + transaction.getAccount_balance_before());
//        System.out.println("Account_balance_after: " + transaction.getAccount_balance_after());
        checkTransaction01(transaction);
    }

    private void checkTransaction01(Transaction transaction) {
        Assert.assertEquals(transaction.getTransaction_type(), TRANSACTION_1.getTransaction_type(),
                "Transaction Type must match");
        Assert.assertEquals(transaction.getTransaction_amount(), TRANSACTION_1.getTransaction_amount(),
                "Transaction Amount must match");
        Assert.assertEquals(transaction.getTransaction_date(), TRANSACTION_1.getTransaction_date(),
                "Transaction Date must match");
    }

    @Test (priority = 13, description = "Create a Transaction2")
    public void createTransactionTest13() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        TransactionService transactionService = new TransactionService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        transactionService.createTransaction(TRANSACTION_2);
        Transaction transaction = transactionService.getTransaction(TRANSACTION_2.getTransaction_id());
        System.out.println("Transaction successfully created: " + transaction.getTransaction_id());
//        System.out.println("Account_balance_before: " + transaction.getAccount_balance_before());
//        System.out.println("Account_balance_after: " + transaction.getAccount_balance_after());
        checkTransaction02(transaction);
    }

    private void checkTransaction02(Transaction transaction) {
        Assert.assertEquals(transaction.getTransaction_type(), TRANSACTION_2.getTransaction_type(),
                "Transaction Type must match");
        Assert.assertEquals(transaction.getTransaction_amount(), TRANSACTION_2.getTransaction_amount(),
                "Transaction Amount must match");
        Assert.assertEquals(transaction.getTransaction_date(), TRANSACTION_2.getTransaction_date(),
                "Transaction Date must match");
    }

    @Test (priority = 14, description = "Create a Transaction3")
    public void createTransactionTest14() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        TransactionService transactionService = new TransactionService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        transactionService.createTransaction(TRANSACTION_3);
        Transaction transaction = transactionService.getTransaction(TRANSACTION_3.getTransaction_id());
        System.out.println("Transaction successfully created: " + transaction.getTransaction_id());
//        System.out.println("Account_balance_before: " + transaction.getAccount_balance_before());
//        System.out.println("Account_balance_after: " + transaction.getAccount_balance_after());
        checkTransaction03(transaction);
    }

    private void checkTransaction03(Transaction transaction) {
        Assert.assertEquals(transaction.getTransaction_type(), TRANSACTION_3.getTransaction_type(),
                "Transaction Type must match");
        Assert.assertEquals(transaction.getTransaction_amount(), TRANSACTION_3.getTransaction_amount(),
                "Transaction Amount must match");
        Assert.assertEquals(transaction.getTransaction_date(), TRANSACTION_3.getTransaction_date(),
                "Transaction Date must match");
    }

    @Test (priority = 15, description = "Create a Transaction4")
    public void createTransactionTest15() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        TransactionService transactionService = new TransactionService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        transactionService.createTransaction(TRANSACTION_4);
    }






    // Update User information

    // Update Account information

    // Update LoanApplication status
    @Test (priority = 16, description = "Update LoanApplication1")
    public void updateLoanApplicationStatusTest16() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        LoanApplicationService loanApplicationService = new LoanApplicationService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        LoanApplication loanApplication
                = loanApplicationService.getLoanApplication(LOAN_APPLICATION_1.getApplication_id());
        loanApplicationService.updateApplicationStatus(loanApplication);
        LoanApplication updatedloanApplication
                = loanApplicationService.getLoanApplication(LOAN_APPLICATION_1.getApplication_id());
        System.out.println("Loan application status updated: " + updatedloanApplication.getApplication_status());
        Assert.assertEquals(updatedloanApplication.getApplication_id(), loanApplication.getApplication_id(),
                "Loan Application id must match");
        Assert.assertEquals(updatedloanApplication.getAmount(), loanApplication.getAmount(),
                "Loan Amount must match");
        Assert.assertNotEquals(updatedloanApplication.getApplication_status(), loanApplication.getApplication_status(),
                "Loan Application Status must update");
    }

    @Test (priority = 17, description = "Update LoanApplication2")
    public void updateLoanApplicationStatusTest17() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        LoanApplicationService loanApplicationService = new LoanApplicationService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        LoanApplication loanApplication
                = loanApplicationService.getLoanApplication(LOAN_APPLICATION_2.getApplication_id());
        loanApplicationService.updateApplicationStatus(loanApplication);
        LoanApplication updatedloanApplication
                = loanApplicationService.getLoanApplication(LOAN_APPLICATION_2.getApplication_id());
        System.out.println("Loan application status updated: " + updatedloanApplication.getApplication_status());
        Assert.assertEquals(updatedloanApplication.getApplication_id(), loanApplication.getApplication_id(),
                "Loan Application id must match");
        Assert.assertEquals(updatedloanApplication.getAmount(), loanApplication.getAmount(),
                "Loan Amount must match");
        Assert.assertNotEquals(updatedloanApplication.getApplication_status(), loanApplication.getApplication_status(),
                "Loan Application Status must update");
    }

    @Test (priority = 18, description = "Update LoanApplication3")
    public void updateLoanApplicationStatusTest18() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        LoanApplicationService loanApplicationService = new LoanApplicationService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        LoanApplication loanApplication
                = loanApplicationService.getLoanApplication(LOAN_APPLICATION_3.getApplication_id());
        loanApplicationService.updateApplicationStatus(loanApplication);
        LoanApplication updatedloanApplication
                = loanApplicationService.getLoanApplication(LOAN_APPLICATION_3.getApplication_id());
        System.out.println("Loan application status updated: " + updatedloanApplication.getApplication_status());
        Assert.assertEquals(updatedloanApplication.getApplication_id(), loanApplication.getApplication_id(),
                "Loan Application id must match");
        Assert.assertEquals(updatedloanApplication.getAmount(), loanApplication.getAmount(),
                "Loan Amount must match");
        Assert.assertNotEquals(updatedloanApplication.getApplication_status(), loanApplication.getApplication_status(),
                "Loan Application Status must update");
    }

    @Test (priority = 19, description = "Update LoanApplication4")
    public void updateLoanApplicationStatusTest19() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        LoanApplicationService loanApplicationService = new LoanApplicationService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        LoanApplication loanApplication
                = loanApplicationService.getLoanApplication(LOAN_APPLICATION_4.getApplication_id());
        loanApplicationService.updateApplicationStatus(loanApplication);
        LoanApplication updatedloanApplication
                = loanApplicationService.getLoanApplication(LOAN_APPLICATION_4.getApplication_id());
        System.out.println("Loan application status updated: " + updatedloanApplication.getApplication_status());
        Assert.assertEquals(updatedloanApplication.getApplication_id(), loanApplication.getApplication_id(),
                "Loan Application id must match");
        Assert.assertEquals(updatedloanApplication.getAmount(), loanApplication.getAmount(),
                "Loan Amount must match");
        Assert.assertNotEquals(updatedloanApplication.getApplication_status(), loanApplication.getApplication_status(),
                "Loan Application Status must update");
    }


    @Test(priority = 30, description = "Delete all Transactions")
    public void deleteAllTransactionsTest30() throws SQLException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        TransactionService transactionService = new TransactionService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        transactionService.setSafeUpdates(0);
        transactionService.deleteAllTransactions();
        transactionService.setSafeUpdates(1);
        transactionService.resetAutoIncrement();
    }

    @Test(priority = 31, description = "Delete all Loan Applications")
    public void deleteAllLoanApplicationsTest31() throws SQLException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        LoanApplicationService loanApplicationService = new LoanApplicationService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        loanApplicationService.setSafeUpdates(0);
        loanApplicationService.deleteAllLoanApplications();
        loanApplicationService.setSafeUpdates(1);
        loanApplicationService.resetAutoIncrement();
    }

    @Test(priority = 32, description = "Delete all Accounts")
    public void deleteAllAccountsTest32() throws SQLException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        AccountService accountService = new AccountService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        accountService.setSafeUpdates(0);
        accountService.deleteAllAccount();
        accountService.setSafeUpdates(1);
        accountService.resetAutoIncrement();
    }
}
