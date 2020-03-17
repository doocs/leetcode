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

    private int idx;
    private int[] preorder;
    private Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.idx = 0;
        this.preorder = preorder;
        int index = 0;
        for (int value : inorder) {
            map.put(value, index ++);
        }
        return buildTree(0, inorder.length);
    }

    private TreeNode buildTree(int left, int right) {
        if (left >= right) return null;
        int rootVal = preorder[idx ++];
        int index = map.get(rootVal);

        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(left, index);
        root.right = buildTree(index + 1, right);
        return root;
    }
}