class Solution {
    public int[] countOppositeParity(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int[] cnt = new int[2];
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i] & 1; // x 的奇偶性
            ans[i] = cnt[x ^ 1]; // 查询右侧奇偶性不等于 x（即 x^1）的元素个数
            cnt[x]++;
        }
        return ans;
    }
}