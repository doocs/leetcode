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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }

    private TreeNode construct(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        int mx = l;
        for (int i = l + 1; i <= r; ++i) {
            if (nums[mx] < nums[i]) {
                mx = i;
            }
        }
        return new TreeNode(nums[mx], construct(nums, l, mx - 1), construct(nums, mx + 1, r));
    }
}