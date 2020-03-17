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

    public int kthSmallest(TreeNode root, int k) {
        return inOrderTraversal(root, new ArrayList<Integer>()).get(k - 1);
    }

    public List<Integer> inOrderTraversal(TreeNode root, List<Integer> list) {
        if (root == null)
            return list;

        inOrderTraversal(root.left, list);
        list.add(root.val);
        inOrderTraversal(root.right, list);
        return list;
    }
}