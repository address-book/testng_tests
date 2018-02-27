package data;

import org.testng.annotations.DataProvider;
import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class UserData {
        private Faker faker = new Faker();

        public Map<String, String> newUser() {
            Map<String, String> data = new HashMap<String, String>();
            data.put("emailAddress", faker.internet().emailAddress());
            data.put("password", faker.internet().password());
            return data;
        }

        @DataProvider(name = "validUser")
        public static Object[][] valid() {
            return new Object[][] {{ "saucecon@example.com", "password" }};
        }
    }

