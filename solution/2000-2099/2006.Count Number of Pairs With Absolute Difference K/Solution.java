class Solution {
    public int countKDifference(int[] nums, int k) {
        int ans = 0;
        int[] counter = new int[110];
        for (int num : nums) {
            if (num >= k) {
                ans += counter[num - k];
            }
            if (num + k <= 100) {
                ans += counter[num + k];
            }
            ++counter[num];
        }
        return ans;
    }
}