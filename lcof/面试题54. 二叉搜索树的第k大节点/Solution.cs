/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int KthLargest(TreeNode root, int k) {
        List<int> list = new List<int>();
        list = postorder(root, list);
        return list[list.Count() - k];
    }

    public List<int> postorder(TreeNode root, List<int> list) {
        if (root == null) {
            return list;
        } else {
            postorder(root.left, list);
            list.Add(root.val);
            postorder(root.right, list);
        }
        return list;
    }

    
}