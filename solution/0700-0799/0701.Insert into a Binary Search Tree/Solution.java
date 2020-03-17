/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {

        if(root == null){
            root = new TreeNode(val);
      }

        if(val < root.val){
            root.left = insertIntoBST(root.left, val);
        }
        else if(val > root.val){
            root.right = insertIntoBST(root.right, val);
        }

        // return the unchanged pointer
        return root;
    }
}
