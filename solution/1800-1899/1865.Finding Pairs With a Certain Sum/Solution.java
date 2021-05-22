class FindSumPairs {
    private int[] nums1;
    private int[] nums2;
    private Map<Integer, Integer> counter;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        counter = new HashMap<>();
        for (int num : nums2) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
    }
    
    public void add(int index, int val) {
        int oldVal = nums2[index];
        counter.put(oldVal, counter.get(oldVal) - 1);
        nums2[index] += val;
        counter.put(oldVal + val, counter.getOrDefault(oldVal + val, 0) + 1);
    }
    
    public int count(int tot) {
        int res = 0;
        for (int num : nums1) {
            res += counter.getOrDefault(tot - num, 0);
        }
        return res;
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */