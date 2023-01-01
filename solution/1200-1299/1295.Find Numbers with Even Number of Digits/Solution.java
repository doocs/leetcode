class Solution {
    public int findNumbers(int[] nums) {
        int ans = 0;
        for (int v : nums) {
            if (String.valueOf(v).length() % 2 == 0) {
                ++ans;
            }
        }
        return ans;
    }
}