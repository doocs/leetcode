class Solution {
    public boolean findSubarrays(int[] nums) {
        Set<Integer> vis = new HashSet<>();
        for (int i = 1; i < nums.length; ++i) {
            if (!vis.add(nums[i - 1] + nums[i])) {
                return true;
            }
        }
        return false;
    }
}