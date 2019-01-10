class Solution {

    private long current = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (isValidBST(root.left) && current < root.val) {
            current = root.val;
            return isValidBST(root.right);
        }
        return false;
    }
}