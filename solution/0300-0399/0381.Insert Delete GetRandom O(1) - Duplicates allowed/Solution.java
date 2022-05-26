class RandomizedCollection {

	private List<Integer> list;
	private Map<Integer, Set<Integer>> map;
	private Random random;

	/** Initialize your data structure here. */
	public RandomizedCollection() {
		list = new ArrayList<>();
		map = new HashMap<>();
		random = new Random();
	}

	/**
	 * Inserts a value to the set. Returns true if the set did not already contain
	 * the specified element.
	 */
	public boolean insert(int val) {
		boolean flag = false;
		if (!map.containsKey(val)) {
			flag = true;
			map.put(val, new HashSet<Integer>());
		}
		map.get(val).add(list.size());
		list.add(val);
		return flag;
	}

	/**
	 * Removes a value from the set. Returns true if the set contained the specified
	 * element.
	 */
	public boolean remove(int val) {
		if (!map.containsKey(val)) {
			return false;
		}
		int removed = map.get(val).iterator().next();
		map.get(val).remove(removed);
		if (removed < list.size() - 1) {
			Integer tail = list.get(list.size() - 1);
			list.set(removed, tail);
			map.get(tail).remove(list.size() - 1);
			map.get(tail).add(removed);
		}
		list.remove(list.size() - 1);
		if (map.get(val).size() == 0) {
			map.remove(val);
		}
		return true;
	}

	/** Get a random element from the set. */
	public int getRandom() {
		if (list.size() == 0) {
			return 0;
		}

		return list.get(random.nextInt(list.size()));
	}

}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */