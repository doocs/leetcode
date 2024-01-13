class Solution {
    public int[] evenOddBit(int n) {
        int[] ans = new int[2];
        for (int i = 0; n > 0; n >>= 1, i ^= 1) {
            ans[i] += n & 1;
        }
        return ans;
    }
}