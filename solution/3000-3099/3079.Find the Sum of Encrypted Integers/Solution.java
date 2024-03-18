class Solution {
    public int sumOfEncryptedInt(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            ans += encrypt(x);
        }
        return ans;
    }

    private int encrypt(int x) {
        int mx = 0, p = 0;
        for (; x > 0; x /= 10) {
            mx = Math.max(mx, x % 10);
            p = p * 10 + 1;
        }
        return mx * p;
    }
}