package hello.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import hello.LexArrayList;
import hello.LexicalUnit;
import hello.LexicalUnitJson;
import hello.MyFileReader;
import hello.Synset;
import hello.XMLConverter;
import hello.model.Echo;
import hello.model.LinerCommand;
import hello.model.XMLReader;

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
	public LexicalUnitJson echo(@RequestParam(value="message", defaultValue=">") String message) throws IOException, MappingException, MarshalException, ValidationException, XPathExpressionException, SAXException, ParserConfigurationException
	{
//		File file = new File(filepath);
//    	XMLReader.setDocAndFactory(file);
    	
		XMLReader xmlr = new XMLReader();
		LexicalUnit lu = xmlr.searchForLexicalUnit("name", message);
		String def = xmlr.getDefinitionFromLexicalUnit(lu);
		List<String> exs = xmlr.getExamplesFromLexicalUnit(lu);
		LexicalUnitJson luj = new LexicalUnitJson();
		luj.setLexicalUnit(lu);
		luj.setDefinition(def);
		luj.setExamples(exs);

		return luj;
	}

	@RequestMapping("/def")
	@ResponseBody
	public String getDefinition(@RequestParam(value="filepath", defaultValue="test.xml") String filepath) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException
	{
		File file = new ClassPathResource(filepath).getFile();
		
		XMLReader xmlr = new XMLReader();
		LexicalUnit lu = xmlr.searchForLexicalUnit("name", "-krotny");
		return xmlr.getDefinitionFromLexicalUnit(lu);
	}
	
	@RequestMapping("/liner2")
	public ArrayList<Integer> executeLiner2(@RequestParam(value="filepath", defaultValue="/opt/liner2.3/test/sentence.xml") String filepath) {
		LinerCommand cmd = new LinerCommand(filepath);
		int val = 1337;
		try {
			val = cmd.run(); //insinde it's exec()
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cmd.getIdxList();
	}
	
	@RequestMapping("/txtfile")
	public String parseCCL(@RequestParam(value="filepath", defaultValue="/opt/liner2.3/test/sentence.xml") String filepath) {
		LinerCommand cmd = new LinerCommand(filepath);
		cmd.parseXML(filepath);
		String response = "";
		ArrayList<String> list = cmd.getText();
		for (String s : list) {
			response += s;
			response += " ";
		}
		return response;
	}
}
