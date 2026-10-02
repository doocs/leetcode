/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int val=0, TreeNode left=null, TreeNode right=null) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Solution {
    public int MaxPathSum(TreeNode root) {
        if (root == null) {
            return -1001;
        }

        int ans = -1001;
        var stack = new Stack<TreeNode>();
        var postorder = new Stack<TreeNode>();
        stack.Push(root);
        while (stack.Count > 0) {
            TreeNode node = stack.Pop();
            postorder.Push(node);
            if (node.left != null) {
                stack.Push(node.left);
            }
            if (node.right != null) {
                stack.Push(node.right);
            }
        }

        var gains = new Dictionary<TreeNode, int>();
        while (postorder.Count > 0) {
            TreeNode node = postorder.Pop();
            int left = node.left == null ? 0 : Math.Max(0, gains[node.left]);
            int right = node.right == null ? 0 : Math.Max(0, gains[node.right]);
            ans = Math.Max(ans, node.val + left + right);
            gains[node] = node.val + Math.Max(left, right);
        }
        return ans;
    }
}
