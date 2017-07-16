package store.locator.storelocator.helper;

import java.util.Comparator;

public class StoreComparator implements Comparator<LWStore> {

	@Override
	public int compare(LWStore o1, LWStore o2) {
		// TODO Auto-generated method stub
		if (o1.getDistance() < o2.getDistance()) {
			return -1;
		} else {
			return 1;
		}
	}
}
