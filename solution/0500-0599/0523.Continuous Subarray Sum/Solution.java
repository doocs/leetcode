class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> d = new HashMap<>();
        d.put(0, -1);
        int s = 0;
        for (int i = 0; i < nums.length; ++i) {
            s = (s + nums[i]) % k;
            if (!d.containsKey(s)) {
                d.put(s, i);
            } else if (i - d.get(s) > 1) {
                return true;
            }
        }
        return false;
    }
}