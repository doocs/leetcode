class Solution {
    private int[] postorder;

    public boolean verifyPostorder(int[] postorder) {
        this.postorder = postorder;
        return dfs(0, postorder.length - 1);
    }

    private boolean dfs(int l, int r) {
        if (l >= r) {
            return true;
        }
        int v = postorder[r];
        int i = l;
        while (i < r && postorder[i] < v) {
            ++i;
        }
        for (int j = i; j < r; ++j) {
            if (postorder[j] < v) {
                return false;
            }
        }
        return dfs(l, i - 1) && dfs(i, r - 1);
    }
}