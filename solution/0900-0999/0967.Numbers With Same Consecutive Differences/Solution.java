class Solution {
    public int[] numsSameConsecDiff(int n, int k) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; ++i) {
            dfs(n - 1, k, i, res);
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    private void dfs(int n, int k, int t, List<Integer> res) {
        if (n == 0) {
            res.add(t);
            return;
        }
        int last = t % 10;
        if (last + k <= 9) {
            dfs(n - 1, k, t * 10 + last + k, res);
        }
        if (last - k >= 0 && k != 0) {
            dfs(n - 1, k, t * 10 + last - k, res);
        }
    }
}