import com.zebrunner.carina.core.IAbstractTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import projects.first_topic.smart_bank_app.userCRUD.CreateUserMethod;
import projects.first_topic.smart_bank_app.userCRUD.DeleteUserMethod;
import projects.first_topic.smart_bank_app.userCRUD.GetUserMethod;
import projects.first_topic.smart_bank_app.userCRUD.UpdateUserMethod;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import javax.net.ssl.*;
import java.security.cert.X509Certificate;

public class UserAPITest implements IAbstractTest {

    @BeforeClass
    public void setup() {
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

            // Configure proxy for Fiddler
            System.setProperty("http.proxyHost", "127.0.0.1");
            System.setProperty("http.proxyPort", "8888");
            System.setProperty("https.proxyHost", "127.0.0.1");
            System.setProperty("https.proxyPort", "8888");

            // Configure RestAssured to trust all certificates
            RestAssured.config = RestAssured.config()
                    .sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Create a User")
    public void createUserTest() {
        CreateUserMethod api = new CreateUserMethod();
        api.replaceUrlPlaceholder("base_url", "https://jsonplaceholder.typicode.com");
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    @Test(description = "Get a User")
    public void getUserTest() {
        GetUserMethod api = new GetUserMethod();
        api.replaceUrlPlaceholder("base_url", "https://jsonplaceholder.typicode.com");
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    @Test(description = "Update a User")
    public void updateUserTest() {
        UpdateUserMethod api = new UpdateUserMethod();
        api.replaceUrlPlaceholder("base_url", "https://jsonplaceholder.typicode.com");
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    @Test(description = "Delete a User")
    public void deleteUserTest() {
        DeleteUserMethod api = new DeleteUserMethod();
        api.replaceUrlPlaceholder("base_url", "https://jsonplaceholder.typicode.com");
        api.callAPIExpectSuccess();
    }
}
