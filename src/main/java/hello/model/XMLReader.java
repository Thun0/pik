package hello.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import hello.LexicalUnit;
import hello.Synset;

public class XMLReader {

	static DocumentBuilderFactory factory;
	static Document doc;
	
	public static void setDocAndFactory(File file) throws SAXException, IOException, ParserConfigurationException
	{
		factory = DocumentBuilderFactory.newInstance();
        doc = factory.newDocumentBuilder().parse(file);
	}
	
	public LexicalUnit searchForLexicalUnit(String attribute, String target) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException
	{
//		factory = DocumentBuilderFactory.newInstance();
//        doc = factory.newDocumentBuilder().parse(file);

        XPathFactory xFactory = XPathFactory.newInstance();
        XPath xPath = xFactory.newXPath();
        
        String xStr = "/array-list/lexical-unit[@" + attribute + "=\"" + target + "\"]";
        
        XPathExpression expr = xPath.compile(xStr);
        
        NodeList nodeList = (NodeList)(expr.evaluate(doc, XPathConstants.NODESET));
        if (nodeList.getLength() >= 1) 
        {
            Node node = nodeList.item(0);
            return nodeToLexicalUnit(node);
        } 
        else 
        	return null;
	}
	
	public Synset searchForSynset(String attribute, String target) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException
	{
//		factory = DocumentBuilderFactory.newInstance();
//        doc = factory.newDocumentBuilder().parse(file);

        XPathFactory xFactory = XPathFactory.newInstance();
        XPath xPath = xFactory.newXPath();
        
        String xStr = "/array-list/synset[@" + attribute + "=\"" + target + "\"]";
        
        XPathExpression expr = xPath.compile(xStr);
        NodeList nodeList = (NodeList)(expr.evaluate(doc, XPathConstants.NODESET));
        if (nodeList.getLength() >= 1) 
        {
            Node node = nodeList.item(0);
            return nodeToSynset(node);
        } 
        else 
        	return null;	
	}
	
	public Synset searchForSynsetByLexicalUnit(File file, String luId) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException
	{
		factory = DocumentBuilderFactory.newInstance();
        doc = factory.newDocumentBuilder().parse(file);

        XPathFactory xFactory = XPathFactory.newInstance();
        XPath xPath = xFactory.newXPath();
        
        String xStr = "/array-list/synset/unit-id[text()='"+luId+"']";
        XPathExpression expr = xPath.compile(xStr);
        NodeList nodeList = (NodeList)(expr.evaluate(doc, XPathConstants.NODESET));
        if (nodeList.getLength() >= 1) 
        {
            Node node = nodeList.item(0).getParentNode();
            return nodeToSynset(node);
        } 
        else 
        	return null;	
	}
	
	public String getDefinitionFromLexicalUnit(LexicalUnit lu)
	{
		String desc = lu.getDesc();
		if(desc.contains("##D:"))
		{
			int pos = desc.indexOf("##D:");
			String sub = desc.substring(pos+5);
			int dot = sub.indexOf(".");
			if(dot == -1)
				return sub;
			return sub.substring(0, dot+1);
		}
		return "Nie znaleziono.";
	}
	
	public List<String> getExamplesFromLexicalUnit(LexicalUnit lu)
	{
		String sub = lu.getDesc();
		List<String> ret = new ArrayList<>();
		while(sub.contains("##W:"))
		{
			int pos = sub.indexOf("##W:");
			sub = sub.substring(pos+5);
			int dot = sub.indexOf(".");
			if(dot == -1)
				ret.add(sub);
			ret.add(sub.substring(0, dot+1));
		}
		return ret;
	}
	
	public LexicalUnit nodeToLexicalUnit(Node node)
	{
		Element elem = (Element) node;
		LexicalUnit lu = new LexicalUnit();
		lu.setId(Integer.parseInt(elem.getAttribute("id")));
		lu.setName(elem.getAttribute("name"));
		lu.setPos(elem.getAttribute("pos"));
		lu.setTagcount(Integer.parseInt(elem.getAttribute("tagcount")));
		lu.setDomain(elem.getAttribute("domain"));
		lu.setDesc(elem.getAttribute("desc"));
		lu.setWorkstate(elem.getAttribute("workstate"));
		lu.setSource(elem.getAttribute("source"));
		lu.setVariant(Integer.parseInt(elem.getAttribute("variant")));
		return lu;
	}
	
	public Synset nodeToSynset(Node node)
	{
		Element elem = (Element) node;
		Synset ss = new Synset();
		ss.setId(Integer.parseInt(elem.getAttribute("id")));
		ss.setWorkstate(elem.getAttribute("workstate"));
		ss.setSplit(Integer.parseInt(elem.getAttribute("split")));
		ss.setOwner(elem.getAttribute("owner"));
		ss.setDefinition(elem.getAttribute("definition"));
		ss.setDesc(elem.getAttribute("desc"));
		ss.setAbstract_(Boolean.parseBoolean(elem.getAttribute("abstract")));
		NodeList unitIds = elem.getElementsByTagName("unit-id");
		int len = unitIds.getLength();
		List<Integer> unids = new ArrayList<>();
		for(int i=0;i<len;++i)
		{
			String top =unitIds.item(i).getTextContent();
			unids.add(Integer.parseInt(top));
		}
		ss.setUnitIds(unids);
		return ss;
	}
}
