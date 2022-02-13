class Solution {
    public int cuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }
        int ans = 1;
        while (n > 4) {
            ans *= 3;
            n -= 3;
        }
        ans *= n;
        return ans;
    }
}