package data_acqusition;

import java.util.Comparator;

public class CustomTreeSetComparator implements Comparator<Integer[]>{

	@Override
	public int compare(Integer[] v1, Integer[] v2) {
		return v1[0] < v2[0] ? -1 : v1[0] > v2[0] ? +1 : 0;
	}

}
