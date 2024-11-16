package projects.first_topic.smart_bank_app;

import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.annotations.Test;
import projects.first_topic.smart_bank_app.CreateUserMethod;

import java.sql.SQLException;

public class UserAPITest implements IAbstractTest {
    @Test(description = "Create a User1")
    public void createUserTest01() throws SQLException {
        CreateUserMethod api = new CreateUserMethod();
        api.replaceUrlPlaceholder("base_url", "http://localhost:8080");
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

}