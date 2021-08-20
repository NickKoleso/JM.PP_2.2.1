package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        Car car1 = new Car("Audi A6", 123456);
        Car car2 = new Car("Volvo XC60", 32434245);
        Car car3 = new Car("BMW M4", 242534535);
        Car car4 = new Car("Mazda CX-9", 42342352);

        userService.add(new User("Александр", "Александров", "aa@mail.ru", car1));
        userService.add(new User("Юрий", "Юрьев", "yy@mail.ru", car2));
        userService.add(new User("Иван", "Иванов", "ii@mail.ru", car3));
        userService.add(new User("Николай", "Николаев", "nn@mail.ru", car4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id: " + user.getId());
            System.out.println("First Name: " + user.getFirstName());
            System.out.println("Last Name: " + user.getLastName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Car: " + user.getUserCar());
            System.out.println();
        }
        System.out.println();
        System.out.println("-----------");
        System.out.println(userService.getUserByCar("Audi A6", 123456));
        System.out.println(userService.getUserByCar("BMW M4", 242534535));
        System.out.println("-----------");


        context.close();
    }
}
