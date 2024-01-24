class Solution {
    public int[] countOfPairs(int n, int x, int y) {
        int[] ans = new int[n];
        x--;
        y--;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int a = j - i;
                int b = Math.abs(i - x) + 1 + Math.abs(j - y);
                int c = Math.abs(i - y) + 1 + Math.abs(j - x);
                ans[Math.min(a, Math.min(b, c)) - 1] += 2;
            }
        }
        return ans;
    }
}