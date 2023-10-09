class Solution {
    public int differenceOfSums(int n, int m) {
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            ans += i % m == 0 ? -i : i;
        }
        return ans;
    }
}