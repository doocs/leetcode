class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        int start = 0, casch = nums[0], longest = 0;
        for (int i = 1; i < nums.length; i++) {
            int nc = nums[i] , con = nc - casch;
            if (con == 0) {
                start++;
            } else if (con != 1) {
                longest = Math.max(i - start, longest);
                start = i;
            }
            casch = nc;
        }
        return Math.max(nums.length - start, longest);
    }
}