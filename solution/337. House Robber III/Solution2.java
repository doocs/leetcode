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
    public int rob(TreeNode root) {
        int[] res = solution(root);
        return Math.max(res[0], res[1]);
    }

    private int[] solution(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int[] left = solution(root.left);
        int[] right = solution(root.right);

        int rob = root.val + left[1] + right[1];
        int notRob = Math.max(left[0], left[1]) +  Math.max(right[0], right[1]);
        return new int[]{rob, notRob};
    }
}