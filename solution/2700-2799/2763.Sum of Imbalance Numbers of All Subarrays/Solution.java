class Solution {
    public int sumImbalanceNumbers(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            TreeMap<Integer, Integer> tm = new TreeMap<>();
            int cnt = 0;
            for (int j = i; j < n; ++j) {
                Integer k = tm.ceilingKey(nums[j]);
                if (k != null && k - nums[j] > 1) {
                    ++cnt;
                }
                Integer h = tm.floorKey(nums[j]);
                if (h != null && nums[j] - h > 1) {
                    ++cnt;
                }
                if (h != null && k != null && k - h > 1) {
                    --cnt;
                }
                tm.merge(nums[j], 1, Integer::sum);
                ans += cnt;
            }
        }
        return ans;
    }
}