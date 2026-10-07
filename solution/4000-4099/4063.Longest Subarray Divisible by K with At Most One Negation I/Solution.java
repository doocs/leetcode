class Solution {
    public int longestSubarray(int[] nums, int k) {
        int ans = f(nums, k);
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = -nums[i];
            ans = Math.max(ans, f(nums, k));
            nums[i] = -nums[i];
        }
        return ans;
    }

    private int f(int[] nums, int k) {
        Map<Integer, Integer> d = new HashMap<>();
        d.put(0, -1);
        int s = 0, res = 0;
        for (int i = 0; i < nums.length; ++i) {
            s = ((s + nums[i]) % k + k) % k;
            if (d.containsKey(s)) {
                res = Math.max(res, i - d.get(s));
            } else {
                d.put(s, i);
            }
        }
        return res;
    }
}