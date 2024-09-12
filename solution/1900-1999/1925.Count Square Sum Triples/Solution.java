class Solution {
    public int countTriples(int n) {
        int ans = 0;
        for (int a = 1; a < n; a++) {
            for (int b = 1; b < n; b++) {
                int x = a * a + b * b;
                int c = (int) Math.sqrt(x);
                if (c <= n && c * c == x) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
