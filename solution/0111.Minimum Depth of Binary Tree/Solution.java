class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        if(root.left==null && root.right==null) return 1;
        int leftDepth = minDepth(root.left);
        if(leftDepth==0) leftDepth = Integer.MAX_VALUE;
        int rightDepth = minDepth(root.right);
        if(rightDepth==0) rightDepth = Integer.MAX_VALUE;
        return Math.min(leftDepth,rightDepth)+1;
    }
}