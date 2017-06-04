package hello;

import java.util.List;

public class LexicalUnitJson 
{
	LexicalUnit lexicalUnit;
	String definition;
	List<String> examples = null;
	
	public LexicalUnit getLexicalUnit() {return lexicalUnit;}
	public void setLexicalUnit(LexicalUnit lexicalUnit) {this.lexicalUnit = lexicalUnit;}
	
	public String getDefinition() {return definition;}
	public void setDefinition(String definition) {this.definition = definition;}
	
	public List<String> getExamples() {return examples;}
	public void setExamples(List<String> examples) {this.examples = examples;}
}
