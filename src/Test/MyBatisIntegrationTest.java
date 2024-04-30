import com.shanxi.water.SpringbootApplication;
import com.shanxi.water.entity.User;
import com.shanxi.water.mapper.UserMapper;
import com.shanxi.water.mongoRepository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class MyBatisIntegrationTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserRepository UserRepository;
    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testInsertUser(){
       User user = new User(1234567,"1234567","jiaobaba",new Date(),1,"管理员");
       userMapper.insertUser(user);
    }
    @Test
    public void testSelectUserById(){
        Object obj = userMapper.getUserById(1234567);
        System.out.println(obj);
    }
    @Test
    public void deleteUser(){
       userMapper.deleteUser(123456);
    }
    @Test
    public void updateUser(){
        User user = new User();
        user.setId(1234567);
        user.setUsername("jiaobaba");
        user.setPassword("1");
        userMapper.updateUser(user);
    }
    @Test
    public void testConnection() {
        redisTemplate.opsForValue().set("testKey", "Hello Redis");
    }
    @Test
    public void testMongoDbConnection() {
        User newUser = new User();
        userRepository.save(newUser);
    }
}
