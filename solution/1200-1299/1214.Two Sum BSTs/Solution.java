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
class Solution {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        List<Integer> vals1 = new ArrayList<>();
        List<Integer> vals2 = new ArrayList<>();
        inorder(root1, vals1);
        inorder(root2, vals2);
        int i = 0, j = vals2.size() - 1;
        while (i < vals1.size() && j >= 0) {
            int s = vals1.get(i) + vals2.get(j);
            if (s == target) {
                return true;
            }
            if (s < target) {
                ++i;
            } else {
                --j;
            }
        }
        return false;
    }

    private void inorder(TreeNode root, List<Integer> vals) {
        if (root != null) {
            inorder(root.left, vals);
            vals.add(root.val);
            inorder(root.right, vals);
        }
    }
}