package hello;

import java.util.List;

public class LexArrayList {

	String owner;
	String date;
	String version;
	List<LexicalUnit> lexicalUnits = null;
	
	public String getOwner() {return owner;}
	public void setOwner(String owner) {this.owner = owner;}
	
	public String getDate() {return date;}
	public void setDate(String date) {this.date = date;}
	
	public String getVersion() {return version;}
	public void setVersion(String version) {this.version = version;}
	
	public List<LexicalUnit> getLexicalUnits() {return lexicalUnits;}
	public void setLexicalUnits(List<LexicalUnit> lexicalUnits){this.lexicalUnits = lexicalUnits;}
}
