class Solution {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        Data data = dfs(root);
        return data.root;
    }

    private Data dfs(TreeNode root) {
        if (root == null) {
            return new Data(null, 0);
        }
        Data left = dfs(root.left);
        Data right = dfs(root.right);
        if (left.d > right.d) return new Data(left.root, 1 + left.d);
        if (left.d < right.d) return new Data(right.root, 1 + right.d);
        return new Data(root, 1 + left.d);
    }

    class Data {
        TreeNode root;
        int d;

        public Data(TreeNode root, int d) {
            this.root = root;
            this.d = d;
        }
    }
}
