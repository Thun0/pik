package hello.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;
import org.springframework.oxm.castor.CastorMarshaller;
import org.json.JSONObject;
import org.json.XML;
import org.junit.Test;
import hello.MyFileReader;
import hello.Synset;
import hello.model.Echo;
import hello.model.LinerCommand;
import hello.ArrayList;
import hello.LexicalUnit;
import hello.XMLConverter;
import hello.model.XMLReader;

import org.exolab.castor.xml.XMLContext; 
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

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
	public Synset echo(@RequestParam(value="message", defaultValue="MESSAGE") String message) throws IOException, MappingException, MarshalException, ValidationException, XPathExpressionException, SAXException, ParserConfigurationException
	{
//		Echo echo = new Echo(message);
//		System.out.println(echo.getMessage());
	
		File file = new ClassPathResource("test.xml").getFile();
		File mapFile = new ClassPathResource("mapping.xml").getFile();

//		Mapping mapping = new Mapping();
//		mapping.loadMapping(mapFile.getAbsolutePath());
//		
//		XMLContext context = new XMLContext();
//		context.addMapping(mapping);
//		
//		
//		Unmarshaller unmarshaller = context.createUnmarshaller();
//		unmarshaller.setClass(ArrayList.class);
//		Marshaller marshaller = context.createMarshaller();
//		
//		XMLConverter converter = new XMLConverter();
//		converter.setMarshaller(marshaller);
//		converter.setUnmarshaller(unmarshaller);
//		
//		ArrayList laret = (ArrayList) converter.convertFromXMLToObject(file.getAbsolutePath());
		///////////////////////////////////
		XMLReader xmlr = new XMLReader();
		Synset ss = xmlr.searchForSynsetByLexicalUnit(file, "102034");

		return ss;
	}
	
	@RequestMapping("/liner2")
	public ArrayList<String> executeLiner2(@RequestParam(value="filepath", defaultValue="/opt/liner2.3/test/sentence.xml") String filepath) {
		LinerCommand cmd = new LinerCommand(filepath);
		try {
			cmd.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cmd.getTokens();
	}
}
