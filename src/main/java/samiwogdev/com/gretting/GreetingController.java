package samiwogdev.com.gretting;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private String history = "";
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

//	@RequestMapping("/greeting")
//	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//		return new Greeting(counter.incrementAndGet(), String.format(template, name));
//	}

//	@RequestMapping("/calculator/add")
//	public String add(@RequestParam("op1") String op1, @RequestParam("op2") String op2) {
//		double op1_d = Double.parseDouble(op1);
//		double op2_d = Double.parseDouble(op2);
//		String result = op1 + " + " + op2 + " = " + String.valueOf(op1_d + op2_d);
//		history = history + result + "<br>";
//		return result;
//	}

	@RequestMapping({ "/greeting", "/greeting/{name}" })
	public Greeting greeting(@PathVariable(value = "name", required = false) String name) {  //Using Path Variables to achieve clean URLs.
		name = (name == null) ? "World" : name;      // Using ternary operator to determine the result
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@RequestMapping(value = "/calculator/add/{op1}/{op2}")
	public String add(@PathVariable String op1, @PathVariable String op2) {  //Using Path Variables to achieve clean URLs.
		double op1_d = Double.parseDouble(op1);
		double op2_d = Double.parseDouble(op2);
		String result = op1 + " + " + op2 + " = " + String.valueOf(op1_d + op2_d);
		history = history + result + "<br>";
		return result;
	}

	@RequestMapping(value = "/calculator/subtract/{op1}/{op2}")
	public String subtract(@PathVariable String op1, @PathVariable String op2) { //Using Path Variables to achieve clean URLs.
		double op1_d = Double.parseDouble(op1);
		double op2_d = Double.parseDouble(op2);
		String result = op1 + " - " + op2 + " = " + String.valueOf(op1_d - op2_d);
		history = history + result + "<br>";
		return result;
	}

	@RequestMapping(value = "/calculator/multiply/{op1}/{op2}")
	public String multiply(@PathVariable String op1, @PathVariable String op2) {  //Using Path Variables to achieve clean URLs.
		double op1_d = Double.parseDouble(op1);
		double op2_d = Double.parseDouble(op2);
		String result = op1 + "  × " + op2 + " = " + String.valueOf(op1_d * op2_d);
		history = history + result + "<br>";
		return result;
	}

	@RequestMapping(value = "/calculator/divide/{op1}/{op2}")
	public String divide(@PathVariable String op1, @PathVariable String op2) {   //Using Path Variables to achieve clean URLs.
		double op1_d = Double.parseDouble(op1);
		double op2_d = Double.parseDouble(op2);
		// Using ternary operator to determine the result
        String result = (op2_d == 0) ? op1 + " / " + op2 + " = " + "Infinity" : op1 + " / " + op2 + " = " + String.valueOf(op1_d / op2_d);
        history = history + result + "<br>";
        return result;
	}

	@RequestMapping("/calculator/history")
	public String history() {
		return history;
	}

	@RequestMapping("/calculator/clear")
	public String clear() {
		history = "";
		return "history cleared";
	}

}
