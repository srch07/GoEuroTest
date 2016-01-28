import com.go_euro.test.utility.Constants;
import com.go_euro.test.utility.InputValidationUtility;
import com.go_euro.test.utility.SaveFileUtility;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Abhishek Anand on 1/28/2016.
 */
public class UnitTestCases {
    @Test
    public void testInputValidationUtility(){
        String [] input = new String[]{"berlin"};
        Assert.assertTrue(InputValidationUtility.isValidate(input));
        input = new String [] {"berlin", "london"};
        Assert.assertTrue(!InputValidationUtility.isValidate(input));
        input = new String [] {};
        Assert.assertTrue(!InputValidationUtility.isValidate(input));
    }

    @Test
    public void testSaveFileUtility(){
        Assert.assertTrue(SaveFileUtility.saveFile(Constants.CSV_FILE_PATH,
                "id,name,type,longitude,latitude", false));
    }


}
