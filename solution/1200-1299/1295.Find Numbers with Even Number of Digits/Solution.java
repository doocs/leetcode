class Solution {
    public int findNumbers(int[] nums) {
        int s = 0;
        for (int num : nums) {
            if ((String.valueOf(num).length() & 1) == 0) {
                ++s;
            }
        }
        return s;
    }
}