package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServeIndex {
	
	@RequestMapping("/app/**")
	public String serveIndex() {
		return "index";
	}
	
}