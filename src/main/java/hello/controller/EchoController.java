package hello.controller;

import java.io.File;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.MyFileReader;
import hello.model.Echo;

@RestController
@RequestMapping("/echo")
public class EchoController {

//	@GetMapping("/")
//    public String listUploadedFiles(Model model) throws IOException {
//
//        model.addAttribute("files", storageService
//                .loadAll()
//                .map(path ->
//                        MvcUriComponentsBuilder
//                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
//                                .build().toString())
//                .collect(Collectors.toList()));
//
//        return "uploadForm";
//    }

	@PostMapping("/echo/file")
    public Echo handleFileUpload(@RequestParam("file") File file) 
	{
		MyFileReader reader = new MyFileReader(file);
		Echo echo = new Echo(reader.readFile());
		System.out.println(echo.getMessage());
        return echo;
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public Echo addEcho(@RequestParam(value="message", defaultValue="MESSAGE") String message)
	{
		Echo echo = new Echo(message);
		System.out.println(echo.getMessage());
		return echo;
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public Echo echo(@RequestParam(value="message", defaultValue="MESSAGE") String message)
	{
		Echo echo = new Echo(message);
		System.out.println(echo.getMessage());
		return echo;
	}
}
