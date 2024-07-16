class Solution {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        TreeNode node = lca(root, startValue, destValue);
        StringBuilder pathToStart = new StringBuilder();
        StringBuilder pathToDest = new StringBuilder();
        dfs(node, startValue, pathToStart);
        dfs(node, destValue, pathToDest);
        return "U".repeat(pathToStart.length()) + pathToDest.toString();
    }

    private TreeNode lca(TreeNode node, int p, int q) {
        if (node == null || node.val == p || node.val == q) {
            return node;
        }
        TreeNode left = lca(node.left, p, q);
        TreeNode right = lca(node.right, p, q);
        if (left != null && right != null) {
            return node;
        }
        return left != null ? left : right;
    }

    private boolean dfs(TreeNode node, int x, StringBuilder path) {
        if (node == null) {
            return false;
        }
        if (node.val == x) {
            return true;
        }
        path.append('L');
        if (dfs(node.left, x, path)) {
            return true;
        }
        path.setCharAt(path.length() - 1, 'R');
        if (dfs(node.right, x, path)) {
            return true;
        }
        path.deleteCharAt(path.length() - 1);
        return false;
    }
}
