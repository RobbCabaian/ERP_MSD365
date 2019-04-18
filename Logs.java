import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Logs 
{
	private Map<String,Product> AssemblyTreeMap;
	private Map<String,Product> NonAssemblyTreeMap;
	
	final private static String PRODUCT_LOG_FILENAME = "ProductLog.txt";
	final private static String EVENT_LOG_FILENAME = "EventLog.txt";
	
	private enum LogEvents
	{
		SOS, POR, MAOS; //Sales Order Save, Purchase Order Receipt, Manufacturing Assembly Order Save
	}
	
	public Logs()
	{
		AssemblyTreeMap = new TreeMap<String, Product>();
		NonAssemblyTreeMap = new TreeMap<String, Product>();
	}
	
	public void add(Product P)
	{
		this.add(P, false);
	}
	
	public void add(Product P, boolean assemblyStatus)
	{
		if(assemblyStatus)
			addToTree(P, AssemblyTreeMap);
		else
			addToTree(P, NonAssemblyTreeMap);
	}
	
	private void addToTree(Product P, Map<String, Product> which)
	{
		if(which.containsKey(P.getID()))
		{
			Product temp = which.remove(P.getID());
			
			temp.setOwner(P.getOwner());
			temp.setQuantity(P.getQuantity()+temp.getQuantity());
			temp.setPrice(P.getPrice());
			
			which.put(temp.getID(), temp);
		}
		else
			which.put(P.getID(), P);
	}
	
	public void remove(Product P, boolean assemblyStatus)
	{
		if(assemblyStatus)
			removeFromTree(P, AssemblyTreeMap);
		else
			removeFromTree(P, NonAssemblyTreeMap);
	}
	
	private void removeFromTree(Product P, Map<String, Product> which)
	{
		which.remove(P.getID());
	}
	
	public void store()
	{
		for(Map.Entry<String, Product> mapEntry : NonAssemblyTreeMap.entrySet())
		{
			String log = mapEntry.getValue().toString();
			
			try
			{
			    FileWriter fw = new FileWriter(PRODUCT_LOG_FILENAME,true);		//Append = true
			    
			    System.out.println("Storing:"+mapEntry.getKey());
			    
			    fw.write(log+"\n");
	            fw.close();
	        }
			catch(FileNotFoundException EX)
			{
	            System.out.println("Output file not found.");
	        }
			catch(IOException ioe)
			{
				System.out.println("IO Exception.");
			}
		}
	}
	
	public Product productIDLookup(String ID)
	{
		return NonAssemblyTreeMap.get(ID);
	}
	
	public Product productIDLookup(String ID, boolean assemblyStatus)
	{
		if(assemblyStatus)
			return AssemblyTreeMap.get(ID);
		else
			return NonAssemblyTreeMap.get(ID);
	}

	public static void main(String[] args)
	{
		Logs newLog = new Logs();
		
		Product P1 = new Product("051717", "PC Laptop", "Robb", 1499.99, 1);
		Product P2 = new Product("051718", "Smart Phone", "Wilson", 799.99, 1);
		Product P3 = new Product("012345", "Water Bottle", "Haroun", 1.25, 5);
		Product P4 = new Product("098765", "Mac Laptop", "Sabrina", 1999.99, 3);
		Product P5 = new Product("052341", "Chair", "Nancy", 29.99, 4);
		
		Product P6 = new Product("052341", "Chair", "Professor Zaidi", 30, 5);
		
		newLog.add(P1);
		newLog.add(P2);
		newLog.add(P3);
		newLog.add(P4);
		newLog.add(P5);
		
		newLog.add(P6);

		newLog.store();
		
		Product P = null;
		try
		{
			P = newLog.productIDLookup("052341");
		}
		catch(NullPointerException NPE)
		{
			System.out.println(NPE.getMessage());
		}
		
		System.out.println(P.toString());
	}
}

