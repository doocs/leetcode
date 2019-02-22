class Solution {
    public void flatten(TreeNode root) {
        if (root==null) return;
        TreeNode right = root.right;
        flatten(right);
        flatten(root.left);
        root.right = root.left;
        root.left = null;
        TreeNode cache = root;
        while (cache.right!=null) cache = cache.right;
        cache.right = right;
    }
}