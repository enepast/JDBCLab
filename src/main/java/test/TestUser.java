package test;

import datos.UserDao;
import domain.User;
import java.util.List;

public class TestUser {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();

//        User user = new User("dario", "nieva");
//        userDao.insert(user);
        User user = new User(4,"nieva", "dario");
        userDao.delete(user);

        List<User> users = userDao.select();

        users.forEach(userAux -> {
            System.out.println("User: " + userAux.toString());
        });

    }

}
