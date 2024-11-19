package projects.first_topic.smart_bank_app.userCRUD;
import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${base_url}/users/${user_id}", methodType = HttpMethodType.DELETE)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class DeleteUserMethod extends AbstractApiMethodV2 {
    public DeleteUserMethod() {
        replaceUrlPlaceholder("user_id", "1"); // Ensure this is the correct user_id
    }
}
