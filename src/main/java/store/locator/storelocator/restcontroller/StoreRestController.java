package store.locator.storelocator.restcontroller;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import store.locator.storelocator.exception.StoreException;
import store.locator.storelocator.exception.StoreNotFoundException;
import store.locator.storelocator.helper.ResponseObject;
import store.locator.storelocator.helper.StoreInfo;
import store.locator.storelocator.service.StoreService;

@RestController
public class StoreRestController {
	@Autowired
	StoreService storeService;

	@RequestMapping(value = "/store", method = RequestMethod.POST, consumes = "application/json")
	ResponseObject<String> createStore(@RequestBody StoreInfo lwStore) {
		ResponseObject<String> res = new ResponseObject<String>();

		try {
			String returnInfo = storeService.createStore(lwStore);

			res.setCode(1);
			res.setDescription("Success");
			res.setData(returnInfo);

			return res;
		}

		catch (StoreException e) {

			res.setCode(52);
			res.setDescription("Failed : " + e.getMessage());
			return res;
			// handling oode goes here

		} catch (Exception e) {

			e.printStackTrace();
			res.setCode(51);
			res.setDescription("Failed : An Error Occured while performing the Task");
			return res;
		}
	}

	@RequestMapping(value = "/store", method = RequestMethod.PUT, consumes = "application/json")
	ResponseObject<StoreInfo> updateSotre(@RequestBody StoreInfo lwStore) {
		ResponseObject<StoreInfo> res = new ResponseObject<StoreInfo>();

		try {
			StoreInfo lwStoreUpdated = storeService.updateStore(lwStore);

			res.setCode(1);
			res.setDescription("Success");
			res.setData(lwStoreUpdated);

			return res;
		}

		catch (StoreNotFoundException e) {

			res.setCode(53);
			res.setDescription("Failed: " + e.getMessage());

			return res;

		} catch (StoreException e) {

			res.setCode(51);
			res.setDescription("Failed : " + e.getMessage());

			return res;

		} catch (Exception e) {

			res.setCode(51);
			res.setDescription("Failed");

			return res;
		}
	}

	@RequestMapping(value = "/store", method = RequestMethod.DELETE, consumes = "application/json")
	ResponseObject<String> deleteStore(@RequestBody StoreInfo lwStore) {
		ResponseObject<String> res = new ResponseObject<String>();

		String storeUID = lwStore.getStoreUID();

		try {
			String returnInfo = storeService.deleteStore(storeUID);

			res.setCode(1);
			res.setDescription("Success");
			res.setData("Successfully Deleted the store" + returnInfo);

			return res;
		}

		catch (StoreNotFoundException e) {

			res.setCode(53);
			res.setDescription("Failed : " + e.getMessage());
			return res;

		} catch (Exception e) {

			e.printStackTrace();
			res.setCode(51);
			res.setDescription("Failed : An Error Occured while performing the Task");
			return res;
		}
	}

	@RequestMapping(value = "/store/locate/{zipcode}", method = RequestMethod.GET, consumes = "application/json")
	ResponseObject<List<StoreInfo>> locateStore(
			@PathVariable("zipcode") Integer zipcode,
			@RequestParam Map<String, String> map) {

		Double radius = Double.parseDouble(map.get("radius"));

		ResponseObject<List<StoreInfo>> res = new ResponseObject<List<StoreInfo>>();

		try {
			List<StoreInfo> stores = storeService.getStoreList(zipcode, radius);

			res.setCode(1);
			res.setDescription("Success");
			res.setData(stores);

			return res;

		} catch (StoreNotFoundException e) {

			res.setCode(53);
			res.setDescription("Failed : " + e.getMessage());
			return res;

		} catch (EntityNotFoundException e) {

			res.setCode(55);
			res.setDescription("Failed : ZipCode does not exits in the database");
			return res;

		} catch (Exception e) {

			e.printStackTrace();
			res.setCode(51);
			res.setDescription("Failed : An Error Occured while performing the Task");
			return res;
		}
	}

}
