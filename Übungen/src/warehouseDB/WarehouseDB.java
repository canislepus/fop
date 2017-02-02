package warehouseDB;

import java.util.HashMap;

import warehouseDB.commodities.Commodity;
import warehouseDB.exceptions.CommodityAlreadyExistsException;
import warehouseDB.exceptions.CommodityDoesNotExistException;
import warehouseDB.exceptions.InsufficientStockException;
import warehouseDB.exceptions.InvalidValueException;

public class WarehouseDB implements IDatabase {
	
	HashMap<String, Commodity> database;
	
	public WarehouseDB(){
		database = new HashMap<String, Commodity>(17);
	}

	@Override
	public void addCommodity(Commodity commodity) throws InvalidValueException, CommodityAlreadyExistsException {
		if(commodity == null)
			throw(new InvalidValueException("InvalidValueException: Attempt to add NULL element."));
		if(database.get(commodity.getName()) != null)
			throw(new CommodityAlreadyExistsException("Commodity" + commodity.getName() + " already exists."));
		
		database.put(commodity.getName(), commodity);

	}

	@Override
	public void removeCommodity(String commodityName) throws InvalidValueException, CommodityDoesNotExistException {
		if(commodityName == null)
			throw(new InvalidValueException("InvalidValueException: Cannot remove element without name."));
		if(database.get(commodityName) == null)
			throw(new CommodityDoesNotExistException("Commodity" + commodityName + " does not exist and therefore not be removed."));
		
		database.remove(commodityName);
	}

	@Override
	public void processOrder(String commodityName, int amount)
			throws InvalidValueException, CommodityDoesNotExistException, InsufficientStockException {
		if(commodityName == null)
			throw(new InvalidValueException("InvalidValueException: Cannot process an order for nothing."));
		if(database.get(commodityName) == null)
			throw(new CommodityDoesNotExistException("Commodity" + commodityName + " does not exist and therefore not be processed."));
		if(database.get(commodityName).getStock() < amount)
			throw(new InsufficientStockException("Insufficient stock.\nRequested: " + amount + "\nIn stock: " + database.get(commodityName).getStock()));
		
		database.get(commodityName).decreaseStock(amount);

	}

}
