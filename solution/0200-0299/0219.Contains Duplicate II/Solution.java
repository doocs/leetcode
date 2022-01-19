class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (mp.containsKey(nums[i]) && i - mp.get(nums[i]) <= k) {
                return true;
            }
            mp.put(nums[i], i);
        }
        return false;
    }
}