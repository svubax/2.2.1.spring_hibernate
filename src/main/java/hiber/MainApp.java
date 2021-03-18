package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
      UserService userService = context.getBean(UserService.class);

      for (int x = 1; x < 100; x += 13){
         String model = x % 2 != 0 ? "Ford" : "Audi";
         userService.add(new User("User" + x, "Lastname" +x, "user" + x + "@mail.ru", new Car(model, x)));
      }

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName() + " Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar().getModel() + " Series = " + user.getCar().getSeries());
         System.out.println();
      }

      Car car = new Car("Audi", 14);
      User user = userService.getCarUser(car);
      System.out.println(user.getFirstName() + " " + user.getLastName());
      context.close();
   }
}
