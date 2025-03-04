class FindSumPairs {
    private int[] nums1;
    private int[] nums2;
    private Map<Integer, Integer> cnt = new HashMap<>();

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        for (int x : nums2) {
            cnt.merge(x, 1, Integer::sum);
        }
    }

    public void add(int index, int val) {
        cnt.merge(nums2[index], -1, Integer::sum);
        nums2[index] += val;
        cnt.merge(nums2[index], 1, Integer::sum);
    }

    public int count(int tot) {
        int ans = 0;
        for (int x : nums1) {
            ans += cnt.getOrDefault(tot - x, 0);
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
