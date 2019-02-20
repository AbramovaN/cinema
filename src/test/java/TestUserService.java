
import com.abramova.cinema.entities.User;
import com.abramova.cinema.service.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;


public class TestUserService {
    private UserService userService = new UserService();;
    private User user;


    @Test
    public void testGetUserByEmail() throws SQLException {
        Assert.assertEquals(true,userService.findUserByEmail("root@com.ua"));
        return;
    }

    @Test
    public void testFindUserTypeIdByEmail() throws SQLException {
        Assert.assertEquals(1,userService.findUserTypeIdByEmail("root@com.ua"));
        return;
    }

    @After
    public void tearDown(){
        userService = null;
        user = null;
    }
}
