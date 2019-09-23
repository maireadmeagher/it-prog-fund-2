import java.util.ArrayList;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Library {

	private ArrayList<DVD> dvds;
	
    public Library()
    {
    	dvds = new ArrayList<DVD>();
    }
	
	public void add(DVD dvd)
	{
		dvds.add(dvd);
	}

	public ArrayList<DVD> getDVDs()
	{
		return dvds;
	}

	public void setDVDs(ArrayList<DVD> dvds)
	{
		this.dvds = dvds;
	}

	/**
	 * listDVDs() - This method prints the index number and the title of each 
	 * element in the dvds ArrayList to the console.
	 */
	public String listDVDs()
	{
		if (dvds.size() == 0)
		{
			return "No DVDs.";
		}
		else
		{
			String listDVDs = "";
			for (int i = 0; i < dvds.size(); i++)
			{
				listDVDs = listDVDs + (i + ":" + dvds.get(i)) + "\n";
			}
			return listDVDs;
		}
		
	}
	
    @SuppressWarnings("unchecked")
    public void load() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("dvd.xml"));
        dvds = (ArrayList<DVD>) is.readObject();
        is.close();
    }
    
    public void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("dvd.xml"));
        out.writeObject(dvds);
        out.close();    
    }
    
}
