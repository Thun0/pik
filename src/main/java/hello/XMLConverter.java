package hello;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;



public class XMLConverter {
	
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	public Marshaller getMarshaller() 
	{
		return marshaller;
	}

	public void setMarshaller(Marshaller marshaller2) 
	{
		this.marshaller = marshaller2;
	}
	
	public Unmarshaller getUnmarshaller() 
	{
		return unmarshaller;
	}
	
	public void setUnmarshaller(Unmarshaller unmarshaller) 
	{
		this.unmarshaller = unmarshaller;
	}
	
	public void convertFromObjectToXML(Object object, String filepath) throws IOException, MarshalException, ValidationException 
	{
		FileWriter writer = null;
		try 
		{
			writer = new FileWriter(filepath);
			getMarshaller().marshal(object, writer);
		} 
		finally 
		{
			if (writer != null) 
			{
				writer.close();
			}
		}	
	}
	
	public Object convertFromXMLToObject(String xmlfile) throws IOException, MarshalException, ValidationException 
	{
		FileReader is = null;
		try 
		{
			is = new FileReader(xmlfile);
			return getUnmarshaller().unmarshal(is);
		} 
		finally 
		{
			if (is != null) 
			{
				is.close();
			}
		}
	}
}
