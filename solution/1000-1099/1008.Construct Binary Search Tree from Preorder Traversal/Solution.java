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
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        // 进入分治法的递归
        return helper(preorder, 0, preorder.length - 1);
    }
    
    private TreeNode helper(int[] preorder, int start, int end) {
        // System.out.println("start: " + start + " end: " + end);
        // 确认递归结束的标志，当 start == end 时，表示该区间只剩下一个 subRoot 节点
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(preorder[start]);
        }
        // 前序遍历，首先遍历到的为根
        TreeNode root = new TreeNode(preorder[start]);
        int leftEnd = start;
        while (leftEnd <= end) {
            if (preorder[leftEnd] > preorder[start]) {
                break;
            }
            leftEnd++;
        }
        // System.out.println("leftEnd:" + leftEnd + " num: " + preorder[leftEnd]);
        root.left = helper(preorder, start + 1, leftEnd - 1);
        root.right = helper(preorder, leftEnd, end);
        return root;
    }
}