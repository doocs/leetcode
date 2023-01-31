class Solution {
    public int[] printNumbers(int n) {
        int[] ans = new int[(int) Math.pow(10, n) - 1];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = i + 1;
        }
        return ans;
    }

    private StringBuilder s = new StringBuilder();
    private List<String> ans = new ArrayList<>();

    public List<String> print(int n) {
        for (int i = 1; i <= n; ++i) {
            dfs(0, i);
        }
        return ans;
    }

    private void dfs(int i, int j) {
        if (i == j) {
            ans.add(s.toString());
            return;
        }
        int k = i > 0 ? 0 : 1;
        for (; k < 10; ++k) {
            s.append(k);
            dfs(i + 1, j);
            s.deleteCharAt(s.length() - 1);
        }
    }
}