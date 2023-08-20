class Solution {
    public int longestEqualSubarray(List<Integer> nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int mx = 0, l = 0;
        for (int r = 0; r < nums.size(); ++r) {
            cnt.merge(nums.get(r), 1, Integer::sum);
            mx = Math.max(mx, cnt.get(nums.get(r)));
            if (r - l + 1 - mx > k) {
                cnt.merge(nums.get(l++), -1, Integer::sum);
            }
        }
        return mx;
    }
}