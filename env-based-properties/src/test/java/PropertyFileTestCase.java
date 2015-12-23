import com.sunshine.util.PropertyFileLoader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by schinta6 on 12/21/15.
 */
public class PropertyFileTestCase {


    @Test
    public void test() {
        assertEquals("Hello World!",PropertyFileLoader.getProperty("test.properties", "welcome.greeting"));
    }


}
