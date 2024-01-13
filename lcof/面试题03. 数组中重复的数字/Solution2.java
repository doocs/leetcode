class Solution {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> vis = new HashSet<>();
        for (int i = 0;; ++i) {
            if (!vis.add(nums[i])) {
                return nums[i];
            }
        }
    }
}