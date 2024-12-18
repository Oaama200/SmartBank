package projects.first_topic.smart_bank_app.services;
import java.sql.*;
import projects.first_topic.smart_bank_app.dao.IUserManagement;
import projects.first_topic.smart_bank_app.exception.DAOException;
import projects.first_topic.smart_bank_app.factory.DAOFactory;
import projects.first_topic.smart_bank_app.model.User;


public class UserService {
    private static IUserManagement iUserManagement = null;

    public UserService(DAOFactory daoFactory) throws DAOException {
        this.iUserManagement = daoFactory.getUserManagement();
    }

    public void createUser(User user) throws SQLException {
        iUserManagement.create(user);
    }

    public void resetAutoIncrement() throws SQLException {
        iUserManagement.resetAutoIncrement();
    }

    public void setSafeUpdates(Integer n) throws SQLException {
        iUserManagement.setSafeUpdates(n);
    }

    public void updateFirstName(User user, String firstName) throws SQLException {
        iUserManagement.updateFirstName(user, firstName);
    }

    public void updateLastName(User user, String lastName) throws SQLException {
        iUserManagement.updateLastName(user, lastName);
    }

    public void updateUserUsername(User user, String username) throws SQLException {
        iUserManagement.updateUserUsername(user, username);
    }

    public void updateUserPassword(User user, String password) throws SQLException{
        iUserManagement.updateUserPassword(user, password);
    }

    public void updateUserType(User user, String type) throws SQLException{
        iUserManagement.updateUserType(user, type);
    }

    public void updateUserPhoneNumber(User user, String phoneNumber) throws SQLException{
        iUserManagement.updateUserPhoneNumber(user, phoneNumber);
    }

    public void updateUserEmail(User user, String email) throws SQLException{
        iUserManagement.updateUserEmail(user, email);
    }

    public void deleteAllUsers() throws SQLException {
        iUserManagement.deleteAllUsers();
    }

    public void deleteUser(User user) throws SQLException {
        iUserManagement.deleteUser(user);
    }

    public User getUser(Integer id) throws SQLException {
        return iUserManagement.findById(id);
    }

    public User getUserByLogin(String username, String password) throws SQLException {
        return iUserManagement.findByLogin(username, password);
    }
}
