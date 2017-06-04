package hello;

import java.util.List;

public class Synset {

	int id;
	String workstate;
	int split;
	String owner;
	String definition;
	String desc;
	boolean abstract_;
	List<Integer> unitIds = null;
	
	public void setId(int id) {this.id = id;}
	public int getId() {return id;}
	
	public void setWorkstate(String workstate) {this.workstate = workstate;}
	public String getWorkstate() {return workstate;}
	
	public void setSplit(int split) {this.split = split;}
	public int getSplit() {return split;}
	
	public void setOwner(String owner) {this.owner = owner;}
	public String getOwner() {return owner;}
	
	public void setDefinition(String definition) {this.definition = definition;}
	public String getDefinition() {return definition;}
	
	public void setDesc(String desc) {this.desc = desc;}
	public String getDesc() {return desc;}
	
	public void setAbstract_(boolean abstract_) {this.abstract_ = abstract_;}
	public boolean getAbstract_() {return abstract_;}
	
	public void setUnitIds(List<Integer> unitIds) {this.unitIds = unitIds;}
	public List<Integer> getUnitIds() {return unitIds;}
}
