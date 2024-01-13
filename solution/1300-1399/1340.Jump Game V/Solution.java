class Solution {
    private int n;
    private int d;
    private int[] arr;
    private Integer[] f;

    public int maxJumps(int[] arr, int d) {
        n = arr.length;
        this.d = d;
        this.arr = arr;
        f = new Integer[n];
        int ans = 1;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, dfs(i));
        }
        return ans;
    }

    private int dfs(int i) {
        if (f[i] != null) {
            return f[i];
        }
        int ans = 1;
        for (int j = i - 1; j >= 0; --j) {
            if (i - j > d || arr[j] >= arr[i]) {
                break;
            }
            ans = Math.max(ans, 1 + dfs(j));
        }
        for (int j = i + 1; j < n; ++j) {
            if (j - i > d || arr[j] >= arr[i]) {
                break;
            }
            ans = Math.max(ans, 1 + dfs(j));
        }
        return f[i] = ans;
    }
}