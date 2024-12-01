import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TaxApplication {

	public static void main(String[] args) {

		// Load the Spring context from the applicationContext.xml
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		// Get the IncomeTax and PropertyTax beans
		Tax incomeTax = (Tax) context.getBean("incomeTax");
		Tax propertyTax = (Tax) context.getBean("propertyTax");

		// Set taxable amounts for both taxes
		incomeTax.setTaxableAmount(100000);  // Example income amount
		propertyTax.setTaxableAmount(200000);  // Example property amount

		// Calculate the tax amounts
		incomeTax.calculateTaxAmount();
		propertyTax.calculateTaxAmount();

		// Print the calculated tax amounts
		System.out.println("Income Tax Amount: " + incomeTax.getTaxAmount());
		System.out.println("Property Tax Amount: " + propertyTax.getTaxAmount());

		// Pay the taxes
		incomeTax.payTax();
		propertyTax.payTax();

		// Try to pay tax again (it should be prevented)
		incomeTax.payTax();
		propertyTax.payTax();
	}
}
