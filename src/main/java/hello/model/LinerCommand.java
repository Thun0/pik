package hello.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LinerCommand {
	
	private String commandStr;
	private ArrayList<String> tokens;
	
	public LinerCommand(String path) {
		commandStr = "/usr/local/bin/liner2 pipe -ini /opt/liner2.3/liner2-models-fat-pack/config-muc.ini -i ccl -f " + path + " -o tuples";
		tokens = new ArrayList<String>();
	}
	
	public int run() throws IOException {
		
		Process cmdProc = Runtime.getRuntime().exec(commandStr);
	
		BufferedReader stdoutReader = new BufferedReader(
		         new InputStreamReader(cmdProc.getInputStream()));
		String line;
		while ((line = stdoutReader.readLine()) != null) {
			line = line.substring(line.indexOf("\"")+1, line.length()-2);
			tokens.add(line);
		}
		
		return cmdProc.exitValue();
	}
	
	private String getToken(String line) {
		int beginning = line.indexOf("\"");
		return line.substring(beginning+1, line.length()-2);
	}
	
	public ArrayList<String> getTokens() {
		return tokens;
	}
}
