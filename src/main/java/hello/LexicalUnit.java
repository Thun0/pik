package hello;

public class LexicalUnit {

	int id;
	String name;
	String pos;
	int tagcount;
	String domain;
	String desc;
	String workstate;
	String source;
	int variant;
	
	public int getId(){return id;}
	public void setId(int id){this.id = id;}
	
	public String getName(){return name;}
	public void setName(String name){this.name = name;}
	
	public String getPos(){return pos;}
	public void setPos(String pos){this.pos = pos;}
	
	public int getTagcount(){return tagcount;}
	public void setTagcount(int tagcount){this.tagcount = tagcount;}
	
	public String getDomain(){return domain;}
	public void setDomain(String domain){this.domain = domain;}
	
	public String getDesc(){return desc;}
	public void setDesc(String desc){this.desc = desc;}
	
	public String getWorkstate(){return workstate;}
	public void setWorkstate(String workstate){this.workstate = workstate;}
	
	public String getSource(){return source;}
	public void setSource(String source){this.source = source;}
	
	public int getVariant(){return variant;}
	public void setVariant(int variant){this.variant = variant;}
}
