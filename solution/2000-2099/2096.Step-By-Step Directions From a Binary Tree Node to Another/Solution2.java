class Solution {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder pathToStart = new StringBuilder();
        StringBuilder pathToDest = new StringBuilder();
        dfs(root, startValue, pathToStart);
        dfs(root, destValue, pathToDest);
        int i = 0;
        while (i < pathToStart.length() && i < pathToDest.length()
            && pathToStart.charAt(i) == pathToDest.charAt(i)) {
            ++i;
        }
        return "U".repeat(pathToStart.length() - i) + pathToDest.substring(i);
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
