public class Solution {
    private int[,] memo;
    private string num;
    private int n;

    public int MinimumOperations(string num) {
        this.num = num;
        n = num.Length;
        memo = new int[n, 25];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 25; j++) {
                memo[i, j] = -1;
            }
        }

        return Dfs(0, 0);
    }

    private int Dfs(int i, int k) {
        if (i == n) {
            return k == 0 ? 0 : n;
        }

        if (memo[i, k] != -1) {
            return memo[i, k];
        }

        // delete current digit
        int res = Dfs(i + 1, k) + 1;

        // keep current digit
        int digit = num[i] - '0';
        int nk = (k * 10 + digit) % 25;
        res = Math.Min(res, Dfs(i + 1, nk));

        memo[i, k] = res;
        return res;
    }
}