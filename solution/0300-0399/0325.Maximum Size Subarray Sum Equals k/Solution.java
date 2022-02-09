class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, -1);
        int s = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i];
            if (mp.containsKey(s - k)) {
                ans = Math.max(ans, i - mp.get(s - k));
            }
            if (!mp.containsKey(s)) {
                mp.put(s, i);
            }
        }
        return ans;
    }
}