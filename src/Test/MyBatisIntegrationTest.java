import com.shanxi.water.SpringbootApplication;
import com.shanxi.water.entity.User;
import com.shanxi.water.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class MyBatisIntegrationTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testInsertUser(){
       User user = new User(123456,"123456","jiaobaba");
       userMapper.insertUser(user);
    }
}
