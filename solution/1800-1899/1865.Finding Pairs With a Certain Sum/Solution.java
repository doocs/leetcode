class FindSumPairs {
    private int[] nums1;
    private int[] nums2;
    private Map<Integer, Integer> cnt = new HashMap<>();

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        for (int v : nums2) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
    }

    public void add(int index, int val) {
        int old = nums2[index];
        cnt.put(old, cnt.get(old) - 1);
        cnt.put(old + val, cnt.getOrDefault(old + val, 0) + 1);
        nums2[index] += val;
    }

    public int count(int tot) {
        int ans = 0;
        for (int v : nums1) {
            ans += cnt.getOrDefault(tot - v, 0);
        }
        return ans;
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */