package hello.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.exolab.castor.xml.XMLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.LexArrayList;
import hello.MyFileReader;
import hello.XMLConverter;
import hello.model.Echo;
import hello.model.LinerCommand;

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

	@Autowired
	private ResourceLoader resourceLoader;
	
	
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
	public LexArrayList echo(@RequestParam(value="message", defaultValue="MESSAGE") String message) throws IOException, MappingException, MarshalException, ValidationException
	{
//		Echo echo = new Echo(message);
//		System.out.println(echo.getMessage());
	
		File file = new ClassPathResource("test.xml").getFile();
		File mapFile = new ClassPathResource("mapping.xml").getFile();

		Mapping mapping = new Mapping();
		mapping.loadMapping(mapFile.getAbsolutePath());
		
		XMLContext context = new XMLContext();
		context.addMapping(mapping);
		
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		unmarshaller.setClass(LexArrayList.class);
		Marshaller marshaller = context.createMarshaller();
		
		XMLConverter converter = new XMLConverter();
		converter.setMarshaller(marshaller);
		converter.setUnmarshaller(unmarshaller);
		
		LexArrayList laret = (LexArrayList) converter.convertFromXMLToObject(file.getAbsolutePath());

		return laret;
	}
	
	@RequestMapping("/liner2")
	public ArrayList<String> executeLiner2(@RequestParam(value="filepath") String filepath) {
		LinerCommand cmd = new LinerCommand(filepath);
		try {
			cmd.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cmd.getTokens();
	}
}
