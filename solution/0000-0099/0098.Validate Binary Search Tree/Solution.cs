public class Solution {
    public bool IsValidBST(TreeNode root) {
        return IsValidBstInternal(root, int.MinValue, int.MaxValue);
    }
    
    private bool IsValidBstInternal(TreeNode root, int min, int max)
    {
        if (root == null) return true;
        if (root.val < min || root.val > max) return false;
        return (root.val == int.MinValue ? root.left == null : IsValidBstInternal(root.left, min, root.val - 1))
            && (root.val == int.MaxValue ? root.right == null : IsValidBstInternal(root.right, root.val + 1, max));
    }
}