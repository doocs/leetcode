class Solution {
    public int minOperations(int[] nums) {
        Set<Integer> st = new HashSet<>();
        for (int i = nums.length - 1; i >= 0; --i) {
            if (!st.add(nums[i])) {
                return i / 3 + 1;
            }
        }
        return 0;
    }
}
