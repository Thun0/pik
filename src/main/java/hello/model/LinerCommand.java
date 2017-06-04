package hello.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LinerCommand {
	
	private String commandStr;
	private ArrayList<String> text;
	private ArrayList<Integer> idxList;
	
	public LinerCommand(String path) {
		commandStr = "/usr/local/bin/liner2 pipe -ini /opt/liner2.3/liner2-models-fat-pack/config-muc.ini -i ccl -f " + path + " -o tuples";
		text = new ArrayList<String>();
		idxList = new ArrayList<Integer>();
		parseXML(path);
	}
	
	public int run() throws IOException {
		Process cmdProc = Runtime.getRuntime().exec(commandStr);
	
		BufferedReader stdoutReader = new BufferedReader(
		         new InputStreamReader(cmdProc.getInputStream()));
		String line;
		int wordIndex = 0;
		int charCount = 0;
		while ((line = stdoutReader.readLine()) != null) {
			String[] indices = line.split(",");
			int idx1 = Integer.parseInt(indices[0].substring(1));
			int idx2 = Integer.parseInt(indices[1]);
			while(idx1 > charCount && wordIndex < text.size()) {
				charCount += text.get(wordIndex).length();
				wordIndex++;
			}
			if(idx1 == charCount) {
				int wordSize = 0;
				while(wordSize < idx2) {
					idxList.add(wordIndex);
					wordSize += text.get(wordIndex).length();
					wordIndex++;
					charCount += wordSize;
				}
				idxList.add(-1337);
			}
		}
		return cmdProc.exitValue();
	}
	
	public ArrayList<Integer> getIdxList() {
		return idxList;
	}
	
	public void parseXML(String path) {
		try {
			File inputFile = new File(path);
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
	        dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("tok");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element element = (Element) nNode;
				text.add(element.getElementsByTagName("orth").item(0).getTextContent());
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
