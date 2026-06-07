class Solution {
    public int sumOfGoodIntegers(int n, int k) {
        int ans = 0;
        int start = Math.max(1, n - k);
        int end = n + k;
        for (int x = start; x <= end; x++) {
            if ((n & x) == 0) {
                ans += x;
            }
        }
        return ans;
    }
}