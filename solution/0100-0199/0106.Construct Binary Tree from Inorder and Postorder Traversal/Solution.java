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
    private Map<Integer, Integer> indexes = new HashMap<>();
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        for (int i = 0; i < n; ++i) {
            indexes.put(inorder[i], i);
        }
        return build(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode build(int[] inorder, int[] postorder, int i1, int i2, int p1, int p2) {
        if (i1 > i2 || p1 > p2) return null;
        int rootVal = postorder[p2];
        int pos = indexes.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        root.left = pos == i1 ? null : build(inorder, postorder, i1, pos - 1, p1, p1 - i1 + pos - 1);
        root.right = pos == i2 ? null : build(inorder, postorder, pos + 1, i2, p1 - i1 + pos, p2 - 1);
        return root;
    }
}