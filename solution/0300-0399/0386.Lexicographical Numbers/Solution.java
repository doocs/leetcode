class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < 10; ++i) {
            dfs(i, n, ans);
        }
        return ans;
    }

    private void dfs(int u, int n, List<Integer> ans) {
        if (u > n) {
            return;
        }
        ans.add(u);
        for (int i = 0; i < 10; ++i) {
            dfs(u * 10 + i, n, ans);
        }
    }
}