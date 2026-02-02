class Solution {
    public int minOperations(int[] nums, int[] target) {
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != target[i]) {
                s.add(nums[i]);
            }
        }
        return s.size();
    }
}
