package net.engineeringdigest.journalApp;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//video-24 for test driven development

@SpringBootTest //to test spring boot context application i.e. JournalApplication.java for testing purpose
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @ParameterizedTest
    @CsvSource({
            "Ram",
            "Sham",
            "Akshit1"
    })
    public void testFindByUserName(String name) {
        User user = userRepository.findByUserName(name);
        assertNotNull(user,"failed for: "+name); //to check if this user is there
        assertTrue(!user.getJournalEntries().isEmpty()); //to check if any journal entries is there
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test(int a,int b, int expected) {
        assertEquals(expected, a+b);
    }
}
