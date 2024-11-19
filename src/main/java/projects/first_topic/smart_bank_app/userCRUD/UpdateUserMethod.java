package projects.first_topic.smart_bank_app.userCRUD;
import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${base_url}/users/${user_id}", methodType = HttpMethodType.PUT)
@RequestTemplatePath(path = "api/users/_put/rq.json")
@ResponseTemplatePath(path = "api/users/_put/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class UpdateUserMethod extends AbstractApiMethodV2 {
    public UpdateUserMethod() {
        super("api/users/_put/rq.json", "api/users/_put/rs.json", "api/users/_put/user.properties");
        replaceUrlPlaceholder("user_id", "1"); // Replace with the actual user_id
    }
}
