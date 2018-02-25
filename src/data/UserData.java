package data;

import org.testng.annotations.DataProvider;

    public class UserData {
        @DataProvider(name = "validUser")
        public static Object[][] valid() {
            return new Object[][] {{ "saucecon@example.com", "password" }};
        }
    }

