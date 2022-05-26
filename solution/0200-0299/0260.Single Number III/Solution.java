class Solution {
    public int[] singleNumber(int[] nums) {
        int eor = 0;
        for (int x : nums) {
            eor ^= x;
        }
        int lowbit = eor & (-eor);
        int[] ans = new int[2];
        for (int x : nums) {
            if ((x & lowbit) == 0) {
                ans[0] ^= x;
            }
        }
        ans[1] = eor ^ ans[0];
        return ans;
    }
}