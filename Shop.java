
/**
 * This class, Shop, creates an item in the shop.
 * Each item has an item name and a price. 
 * @author diyaa
 *
 */
public class Shop {
	
	private String items;
	private int prices;
	
	public Shop(String item, int price) {
		items = item;
		prices = price;
	}
	
	//getters of item name and price
	public String getItem() { return items; }
	public int getPrice() { return prices; }
	
}
