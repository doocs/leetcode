/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class FindElements {
    private Set<Integer> nodes;

    public FindElements(TreeNode root) {
        nodes = new HashSet<>();
        root.val = 0;
        nodes.add(0);
        dfs(root);
    }
    
    public boolean find(int target) {
        return nodes.contains(target);
    }
    
    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            root.left.val = root.val * 2 + 1;
            nodes.add(root.left.val);
        }
        if (root.right != null) {
            root.right.val = root.val * 2 + 2;
            nodes.add(root.right.val);
        }
        dfs(root.left);
        dfs(root.right);
    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */