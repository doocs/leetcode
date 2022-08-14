class Solution {
    public int countSpecialNumbers(int n) {
        List<Integer> digits = new ArrayList<>();
        while (n != 0) {
            digits.add(n % 10);
            n /= 10;
        }
        int m = digits.size();
        int ans = 0;
        for (int i = 1; i < m; ++i) {
            ans += 9 * A(9, i - 1);
        }
        boolean[] vis = new boolean[10];
        for (int i = m - 1; i >= 0; --i) {
            int v = digits.get(i);
            for (int j = i == m - 1 ? 1 : 0; j < v; ++j) {
                if (vis[j]) {
                    continue;
                }
                ans += A(10 - (m - i), i);
            }
            if (vis[v]) {
                break;
            }
            vis[v] = true;
            if (i == 0) {
                ++ans;
            }
        }
        return ans;
    }

    private int A(int m, int n) {
        return n == 0 ? 1 : A(m, n - 1) * (m - n + 1);
    }
}