class Solution {
    private int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }
    private int helper(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            max = Math.max(max, root.val);
            return root.val;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        int currSum = Math.max(Math.max(left + root.val, right + root.val), root.val);
        max = Math.max(Math.max(currSum, left + right + root.val), max);
        return currSum;
    }
}