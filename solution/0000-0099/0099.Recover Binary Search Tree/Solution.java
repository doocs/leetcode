class Solution {
    private TreeNode first,second,pre;
    public void recoverTree(TreeNode root) {
        traverse(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    private void traverse(TreeNode root) {
        if (root == null) return;
        traverse(root.left);
        if (pre != null) {
            if (first == null && pre.val > root.val) first = pre;
            if (first != null && pre.val > root.val) second = root;
        }
        pre = root;
        traverse(root.right);
    }
}