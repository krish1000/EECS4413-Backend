//package ecommerceBackend.repository;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import ecommerceBackend.entity.Address;
//import ecommerceBackend.entity.Employee;
//import ecommerceBackend.entity.Item;
//import ecommerceBackend.entity.Order;
//import ecommerceBackend.entity.ShoppingCart;
//import ecommerceBackend.entity.Status;
//import ecommerceBackend.entity.User;
//
//@Configuration
//public class LoadDatabase {
//
//    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
//
//    @Bean
//    public CommandLineRunner initDatabase(
//    		EmployeeRepository employeeRepository,
//    		OrderRepository orderRepository,
//    		ItemRepository itemRepository,
//    		AddressRepository addressRepository,
//    		UserRepository userRepository,
//    		ShoppingCartRepository shoppingCartRepository
//    ) {
//
//        return args -> {
//        	// tutorial examples (NEED TO REMOVE LATER, KEEP NOW JUST FOR EXAMPLE TO REUSE)
//            log.info("Preloading " + employeeRepository.save(new Employee("Bilbo", "Baggins", "burglar")));
//            log.info("Preloading " + employeeRepository.save(new Employee("Frodo", "Baggins", "thief")));
//            log.info("Preloading " + employeeRepository.save(new Employee("Kim Long", "Ngo ", "software engineer")));
//            
//            employeeRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));
//
//            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
//            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));
//
//            orderRepository.findAll().forEach(order -> {
//              log.info("Preloaded " + order);
//            });
//            
//            /****This part is actually for our ecommerce proj****/
//
//            //-----Item Example
//            /*e.g args 	public Item(String name, String description, String type, String brand, int quantity, double price)*/
//            log.info("Preloading " + itemRepository.save(new Item("harry potter", "book about a guy with glasses", "book", "Scholastic", 75, 12.5)));
//            log.info("Preloading " + itemRepository.save(new Item("iPad", "a device for personal use", "tablet/computer", "Apple", 10, 399.99)));
//            log.info("Preloading " + itemRepository.save(new Item("GalaxyPad", "a device for personal use", "tablet/computer", "Samsung", 15, 300.00)));
//            log.info("Preloading " + itemRepository.save(new Item("S22", "a device for calling", "phone", "Samsung", 25, 900.00)));
//            log.info("Preloading " + itemRepository.save(new Item("iphone 14", "a device for calling", "phone", "Apple", 35, 1400.00)));
//
//            
//            itemRepository.findAll().forEach(item -> log.info("Preloaded " + item));
//            
//            //-----User Example
//            //This has to be before address so we can use the userID into Address Objects
//            User john = new User("John", "Smith");
//            User krish = new User("Krish", "Was Not Here");
//            ShoppingCart johnSC = new ShoppingCart(john.getId());
//            ShoppingCart krishSC = new ShoppingCart(john.getId());
////            log.info("Preloading " + shoppingCartRepository.save(johnSC));
////            log.info("Preloading " + shoppingCartRepository.save(krishSC));
////            log.info("Preloading " + userRepository.save(new User(john)));
////            log.info("Preloading " + userRepository.save(new User(krish)));
//            
//            //-----Address Example
//            /*e.g args 	public Address(Long userID, String street, String province, String country, String zip, String phone) {*/
//            Address johnAddress = new Address((long)999, "101 Pond Street", "Ontario", "Canada", "M3J 1P3", "416-999-9999");
//            Address krishAddress = new Address((long)888, "4207 Keele Street", "Ontario", "Canada", "M3J 1P3", "416-888-8989");
////            log.info("Preloading " + addressRepository.save(new Address(johnAddress)));
////            log.info("Preloading " + addressRepository.save(new Address(krishAddress)));
//            
//       
//            addressRepository.findAll().forEach(address -> log.info("Preloaded " + address));
//        };
//    }
//}