public class Product implements Comparable
{
	private String productID;
	private String productName;	
	private String owner;
	private String previousOwner;
	
	private int quantity;
	
	private double pricePerUnit;
	private double totalPrice;
	
	private boolean assembly;
	
	public Product(String ID, String name, String owner, double price, int quantity)
	{
		this.productID = ID;
		this.productName = name;
		this.pricePerUnit = price;
		this.owner = owner;
		this.previousOwner = null;
		this.quantity = quantity;
		totalPrice = this.pricePerUnit * this.quantity;
		assembly = false;
	}
	
	public String getID()
	{
		return productID;
	}
	
	public String getName()
	{
		return productName;
	}
	
	public double getPrice()
	{
		return pricePerUnit;
	}
	
	public void setPrice(double newPrice)
	{
		pricePerUnit = newPrice;
		totalPrice = pricePerUnit * quantity;
	}
	
	public void setOwner(String owner)
	{
		this.previousOwner = this.owner;
		this.owner = owner;
	}
	
	public String getOwner()
	{
		return owner;
	}
	
	public String getPreviousOwner()
	{
		return previousOwner;
	}
	
	public double getTotalPrice()
	{
		totalPrice = pricePerUnit * quantity;
		return totalPrice;
	}
	
	public void setTotalPrice()
	{
		totalPrice = pricePerUnit * quantity;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	
	public void setAssembly(boolean assemblyStatus)
	{
		assembly = assemblyStatus;
	}
	
	public boolean isAssembly()
	{
		return assembly;
	}
	
	@Override
	public int compareTo(Object O)
	{
		if(O instanceof Product)
			return productID.compareTo(((Product)(O)).productID);
		else
			return -1;
	}
	
	@Override
	public String toString()
	{
		String theString = "{\n\t\"productID\":\""+productID + "\"\n\t\"productName\":\""+productName
		+ "\"\n\t\"owner\":\""+ owner + "\"\n\t\"previousOwner\":\""+previousOwner
		+ "\"\n\t\"quantity\":"+ quantity + "\n\t\"pricePerUnit\":"+pricePerUnit + "\n\t\"totalPrice\":"+totalPrice+"\n}";
		
		return theString;
	}
}
