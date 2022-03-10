class Solution {
    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length < 2) {
            return true;
        }
        return dfs(postorder, 0, postorder.length);
    }

    private boolean dfs(int[] postorder, int i, int n) {
        if (n <= 0) {
            return true;
        }
        int v = postorder[i + n - 1];
        int j = i;
        while (j < i + n && postorder[j] < v) {
            ++j;
        }
        for (int k = j; k < i + n; ++k) {
            if (postorder[k] < v) {
                return false;
            }
        }
        return dfs(postorder, i, j - i) && dfs(postorder, j, n + i - j - 1);
    }
}