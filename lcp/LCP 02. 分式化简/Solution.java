class Solution {
    public int[] fraction(int[] cont) {
        return dfs(cont, 0);
    }

    private int[] dfs(int[] cont, int i) {
        if (i == cont.length - 1) {
            return new int[] {cont[i], 1};
        }
        int[] ans = dfs(cont, i + 1);
        int a = ans[0], b = ans[1];
        return new int[] {a * cont[i] + b, a};
    }
}