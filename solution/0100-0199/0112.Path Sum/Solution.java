class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        sum -= root.val;
        return (root.left == null && root.right == null && sum == 0) ||
                hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }
}