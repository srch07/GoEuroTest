import com.go_euro.test.configuration.Constants;
import com.go_euro.test.utility.InputValidationUtility;
import com.go_euro.test.utility.SaveFileUtility;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Created by Abhishek Anand on 1/28/2016.
 */
public class UnitTestSpecification {
    @Test
    public void testInputValidationUtility(){
        String [] input = new String[]{"berlin"};
        Assert.assertTrue(InputValidationUtility.isValidate(input));
        input = new String [] {"berlin", "london"};
        Assert.assertFalse(InputValidationUtility.isValidate(input));
        input = new String [] {};
        Assert.assertFalse(InputValidationUtility.isValidate(input));
    }

    @Test
    public void testSaveFileUtility(){
        Assert.assertTrue(SaveFileUtility.save(Constants.CSV_FILE_PATH,
                "id,name,type,longitude,latitude", Constants.APPEND_ALLOWED_TO_SAVE_CSV_DATA));
        File file = new File(Constants.CSV_FILE_PATH);
        if(file.exists())
            file.delete();
    }


}
