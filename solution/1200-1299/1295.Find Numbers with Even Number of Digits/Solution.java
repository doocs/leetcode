class Solution {
    public int findNumbers(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res += (String.valueOf(num).length() & 1) == 0 ? 1 : 0;
        }
        return res;
    }
}