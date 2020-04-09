class Solution {
    public String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        if (t.right != null) {
            return t.val + "(" + tree2str(t.left) + ")" + "(" + tree2str(t.right) + ")";
        }
        if (t.left != null) {
            return t.val + "(" + tree2str(t.left) + ")";
        }
        return t.val + "";
    }
}
