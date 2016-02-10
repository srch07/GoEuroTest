import com.go_euro.test.service.APIService;
import com.go_euro.test.service.ServiceLocator;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Abhishek Anand on 1/28/2016.
 */
public class IntegrationSpecification {
    static final APIService apiService = ServiceLocator.getApiService();
    @Test
    public void testAPICall(){
        Assert.assertTrue(apiService.invoke("berlin"));
    }
}
