class Solution {
    public int minimumOperations(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int i = nums.length - 1; i >= 0; --i) {
            if (s.contains(nums[i])) {
                return i / 3 + 1;
            }
            s.add(nums[i]);
        }
        return 0;
    }
}
