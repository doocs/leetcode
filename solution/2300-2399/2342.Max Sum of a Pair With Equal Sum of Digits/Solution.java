class Solution {
    public int maximumSum(int[] nums) {
        Map<Integer, List<Integer>> d = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            int v = nums[i];
            int t = 0;
            while (v != 0) {
                t += v % 10;
                v /= 10;
            }
            d.computeIfAbsent(t, k -> new ArrayList<>()).add(nums[i]);
        }
        int ans = -1;
        for (List<Integer> v : d.values()) {
            int n = v.size();
            if (n > 1) {
                Collections.sort(v);
                ans = Math.max(ans, v.get(n - 1) + v.get(n - 2));
            }
        }
        return ans;
    }
}