class Solution {
    public int reverse(int x) {
        int ans = 0;
        for (; x != 0; x /= 10) {
            if (ans < Integer.MIN_VALUE / 10 || ans > Integer.MAX_VALUE / 10) {
                return 0;
            }
            ans = ans * 10 + x % 10;
        }
        return ans;
    }
}