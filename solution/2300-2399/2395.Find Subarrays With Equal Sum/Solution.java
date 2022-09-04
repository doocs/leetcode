class Solution {
    public boolean findSubarrays(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < nums.length - 1; ++i) {
            int v = nums[i] + nums[i + 1];
            if (!s.add(v)) {
                return true;
            }
        }
        return false;
    }
}