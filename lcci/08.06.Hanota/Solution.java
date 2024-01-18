class Solution {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        dfs(A.size(), A, B, C);
    }

    private void dfs(int n, List<Integer> a, List<Integer> b, List<Integer> c) {
        if (n == 1) {
            c.add(a.remove(a.size() - 1));
            return;
        }
        dfs(n - 1, a, c, b);
        c.add(a.remove(a.size() - 1));
        dfs(n - 1, b, a, c);
    }
}