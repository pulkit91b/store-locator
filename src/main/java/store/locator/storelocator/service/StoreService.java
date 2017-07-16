package store.locator.storelocator.service;

import java.util.List;

import store.locator.storelocator.exception.StoreException;
import store.locator.storelocator.helper.LWStore;

public interface StoreService {

	/**
	 * 
	 * @param lwStore
	 * @return String "Success" on successful creation of store
	 * @throws StoreException
	 */
	String createStore(LWStore lwStore) throws StoreException;

	/**
	 * 
	 * @param lwStore
	 * @return LWStore , updated store
	 * @throws StoreException
	 */
	LWStore updateStore(LWStore lwStore) throws StoreException;

	/**
	 * 
	 * @param storeUID
	 * @return String
	 * @throws StoreException
	 */
	String deleteStore(String storeUID) throws StoreException;

	/**
	 * 
	 * @param zipcode
	 * @param radius
	 * @return List<LWStore> sorted with nearest store first.
	 * @throws StoreException
	 */
	List<LWStore> getStoreList(Integer zipcode, Double radius)
			throws StoreException;

}
