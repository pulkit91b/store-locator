package store.locator.storelocator.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import store.locator.storelocator.exception.StoreException;
import store.locator.storelocator.exception.StoreNotFoundException;
import store.locator.storelocator.helper.StoreInfo;
import store.locator.storelocator.helper.StoreComparator;
import store.locator.storelocator.helper.StoreHelper;
import store.locator.storelocator.model.Store;
import store.locator.storelocator.model.ZipMaster;
import store.locator.storelocator.repository.StoreRepository;
import store.locator.storelocator.repository.ZipMasterRepository;

@Service("storeService")
public class StoreServiceImpl implements StoreService {

	@Autowired
	StoreRepository storeRepo;

	@Autowired
	ZipMasterRepository zipMasterRepo;

	@Override
	public String createStore(StoreInfo lwStore) throws StoreException {
		// TODO Auto-generated method stub

		validateStore(lwStore);

		// check for duplicates; attribute : storeUID
		if (isStorePresent(lwStore.getStoreUID())) {
			throw new StoreException("Store with " + lwStore.getStoreUID()
					+ "is already present");
		}

		Store store = new Store();
		BeanUtils.copyProperties(lwStore, store);
		storeRepo.save(store);

		return "Success";
	}

	@Override
	public StoreInfo updateStore(StoreInfo lwStore) throws StoreException {
		// TODO Auto-generated method stub

		validateStore(lwStore);

		Store store = storeRepo.findByStoreUID(lwStore.getStoreUID());
		if (null == store) {

			throw new StoreNotFoundException("Store with storeUID"
					+ lwStore.getStoreUID() + " does not exists");
		} else {

			BeanUtils.copyProperties(lwStore, store);
			store = storeRepo.save(store);
			BeanUtils.copyProperties(store, lwStore, "storeId");

			return lwStore;
		}
	}

	@Override
	public String deleteStore(String storeUID) throws StoreException,
			EntityNotFoundException {
		// TODO Auto-generated method stub

		storeRepo.deleteByStoreUID(storeUID);

		return "";
	}

	@Override
	public List<StoreInfo> getStoreList(Integer zipcode, Double radius)
			throws StoreException {
		// TODO Auto-generated method stub

		List<StoreInfo> lwStores = new ArrayList<StoreInfo>();

		ZipMaster zipMaster = zipMasterRepo.getOne(zipcode);

		Double latiZip = zipMaster.getLatitude();
		Double longZip = zipMaster.getLongitude();

		List<Store> stores = storeRepo.findAll();

		for (Store store : stores) {

			Double distance = StoreHelper.distance(latiZip, longZip,
					store.getLatitude(), store.getLongitute());

			// to check whether store exists within radius
			if (distance < radius) {

				StoreInfo lwStore = new StoreInfo();
				BeanUtils.copyProperties(store, lwStore, "storeId");
				lwStore.setDistance(distance);

				lwStores.add(lwStore);
			}

		}

		Collections.sort(lwStores, new StoreComparator());

		return lwStores;
	}

	/**
	 * This function validates the inputs : storeUID , longitude and latitude.
	 * 
	 * @param lwStore
	 * @throws StoreException
	 */
	private void validateStore(StoreInfo lwStore) throws StoreException {
		// TODO Auto-generated method stub

		// null check for storeUID
		if (StringUtils.isEmpty(lwStore.getStoreUID())) {

			throw new StoreException(
					"Store Unique Identifier must not be null.");
		}

		// null check for longitude and latitude
		if (StringUtils.isEmpty(lwStore.getLongitute())
				|| StringUtils.isEmpty(lwStore.getLatitude())) {

			throw new StoreException(
					"Both longitute and latitute are mandatory");
		}
	}

	/**
	 * This Function returns true if store is configured in the database.
	 * 
	 * @param storeUID
	 * @return Boolean
	 */
	Boolean isStorePresent(String storeUID) {

		Store store = storeRepo.findByStoreUID(storeUID);

		if (null != store)
			return true;

		else
			return false;
	}
}
