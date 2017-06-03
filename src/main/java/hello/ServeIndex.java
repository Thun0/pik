package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServeIndex {

	@RequestMapping("/")
	public String returnIndex() {
		return "index.html";
	}
	
	@RequestMapping("/app/**")
	public String redirectToIndex() {
		return "forward:/";
	}
	
}