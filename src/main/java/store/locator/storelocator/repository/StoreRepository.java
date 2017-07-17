package store.locator.storelocator.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import store.locator.storelocator.model.Store;

@Transactional
public interface StoreRepository extends JpaRepository<Store, Integer> {

	Store findByStoreUID(String storeUID);

	Integer deleteByStoreUID(String StoreUID);
}
