package ecommerceBackend;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//import payroll.application.PayrollApplication; //original tutorial
import ecommerceBackend.application.StoreApplication;

public class ServletInitializer extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(PayrollApplication.class); // original tutorial code
		return application.sources(StoreApplication.class);
	}
	
}