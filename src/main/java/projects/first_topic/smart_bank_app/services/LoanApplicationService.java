package projects.first_topic.smart_bank_app.services;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import projects.first_topic.smart_bank_app.constant.ProjectConstant;
import projects.first_topic.smart_bank_app.dao.IAccountManagement;
import projects.first_topic.smart_bank_app.dao.ILoanApplicationManagement;
import projects.first_topic.smart_bank_app.dao.IUserManagement;
import projects.first_topic.smart_bank_app.exception.DAOException;
import projects.first_topic.smart_bank_app.factory.DAOFactory;
import projects.first_topic.smart_bank_app.model.*;



public class LoanApplicationService {
    private static ILoanApplicationManagement iLoanApplicationManagement = null;

//    public LoanApplicationService(DAOFactory daoFactory) throws DAOException {
//        this.iLoanApplicationManagement = daoFactory.getLoanApplicationManagement();
//    }
public LoanApplicationService(DAOFactory daoFactory) throws DAOException {
    this.iLoanApplicationManagement = daoFactory.getLoanApplicationManagement();
}

    public void createLoanApplication(LoanApplication loanApplication) throws SQLException {
        iLoanApplicationManagement.create(loanApplication);
    }

    public void resetAutoIncrement() throws SQLException {
        iLoanApplicationManagement.resetAutoIncrement();
    }

    public void setSafeUpdates(Integer n) throws SQLException {
        iLoanApplicationManagement.setSafeUpdates(n);
    }

    public static void updateApplicationStatus(LoanApplication loanApplication) throws SQLException {
        iLoanApplicationManagement.updateLoanApplicationStatus(loanApplication);
    }

    public void deleteAllLoanApplications() throws SQLException {
        iLoanApplicationManagement.deleteAllLoanApplications();
    }

    public void deleteLoanApplication(LoanApplication loanApplication) throws SQLException {
        iLoanApplicationManagement.deleteLoanApplication(loanApplication);
    }

    public LoanApplication getLoanApplication(Integer id) throws SQLException {
        return iLoanApplicationManagement.findById(id);
    }
    public static void applyForLoan(Integer userId, String loanType, double amount, String startDate, String endDate) throws SQLException {

        UserService userService = new UserService(DAOFactory.getDAOFactory(ProjectConstant.MYSQL));
        User user = userService.getUser(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setUser_id(userId);
        loanApplication.setLoan_type(loanType);
        loanApplication.setAmount(amount);
        loanApplication.setStart_date(startDate);
        loanApplication.setEnd_date(endDate);
        loanApplication.setApplication_date(LocalDate.now().toString());

       // createLoanApplication(loanApplication);
        LoanApplicationService loanApplicationService;
        // Create the application
        try {
            loanApplicationService = new LoanApplicationService(DAOFactory.getDAOFactory(ProjectConstant.MYSQL));
        } catch (SQLException e) {
            System.out.println("Error while creating loan application:" + e.getMessage());
            return;
        }
        loanApplicationService.createLoanApplication(loanApplication);

        // Assess eligibility
        assessLoanEligibility(loanApplication);

        // Print result
        LoanApplication updatedApplication = loanApplicationService.getLoanApplication(loanApplication.getApplication_id());
        System.out.println("Loan application status: " + updatedApplication.getApplication_status());
    }

    private static void assessLoanEligibility(LoanApplication loanApplication) throws SQLException {
        UserService userService;
        try {
            userService = new UserService(DAOFactory.getDAOFactory(ProjectConstant.MYSQL));
        } catch (SQLException e) {
            System.out.println("Error while creating user:" + e.getMessage());
            return;
        }
        User user = userService.getUser(loanApplication.getUser_id());
        if (user == null) {
            throw new IllegalArgumentException("User not found during assessment");
        }

        // Calculate total current balance
        AccountService accountService;
        try {
            accountService = new AccountService(DAOFactory.getDAOFactory(ProjectConstant.MYSQL));
        } catch (SQLException e) {
            System.out.println("Error while finding account:" + e.getMessage());
            return;
        }

        double totalBalance = accountService.totalUserBalance(user.getUser_id());
        double debtToIncomeRatio = calculateDebtToIncomeRatio(user, loanApplication.getAmount());

//        if (debtToIncomeRatio <= 0.43 && user.getCredit_score() >= 650) {
//            loanApplication.setApplication_status("approved");
//        } else {
//            loanApplication.setApplication_status("declined");
//        }
        // Decision logic
        boolean approved = false;
        if (user.getCredit_score() >= 750 && debtToIncomeRatio <= 0.45) {
            approved = true;
        } else if (user.getCredit_score() >= 700 && debtToIncomeRatio <= 0.40 && totalBalance > loanApplication.getAmount() * 0.2) {
            approved = true;
        } else if (user.getCredit_score() >= 650 && debtToIncomeRatio <= 0.35 && totalBalance > loanApplication.getAmount() * 0.3) {
            approved = true;
        }
        loanApplication.setApplication_status(approved ? "approved" : "declined");
        LoanApplicationService loanApplicationService;
        try {
            loanApplicationService = new LoanApplicationService(DAOFactory.getDAOFactory(ProjectConstant.MYSQL));
        } catch (SQLException e) {
            System.out.println("Error while creating loan application:" + e.getMessage());
        }
        iLoanApplicationManagement.updateLoanApplicationStatus(loanApplication);
        //updateApplicationStatus(loanApplication);
    }

    private static double calculateDebtToIncomeRatio(User user, double newLoanAmount) {
//        double monthlyIncome = user.getAnnual_income() / 12;
//        double totalMonthlyDebt = (user.getLoan_amount() + newLoanAmount) / 12; // Simplified calculation
//        return totalMonthlyDebt / monthlyIncome;
        double monthlyIncome = user.getAnnual_income() / 12;
        double existingMonthlyDebt = user.getLoan_amount() / 12; // Existing loans
        double newMonthlyDebt = newLoanAmount / 12; // New loan amount
        return (existingMonthlyDebt + newMonthlyDebt) / monthlyIncome;
    }
}
