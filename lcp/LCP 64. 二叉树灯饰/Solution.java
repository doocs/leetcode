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
    public int closeLampInTree(TreeNode root) {
        return dfs(root)[0];
    }

    private int[] dfs(TreeNode root) {
        int[] ans = new int[4];
        if (root == null) {
            return ans;
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int l1 = left[0], l2 = left[1], l3 = left[2], l4 = left[3];
        int r1 = right[0], r2 = right[1], r3 = right[2], r4 = right[3];
        if (root.val != 0) {
            ans[0] = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 1, l4 + r4 + 3);
            ans[1] = min(l1 + r1 + 2, l2 + r2, l3 + r3 + 2, l4 + r4 + 2);
            ans[2] = min(l1 + r1, l2 + r2 + 2, l3 + r3 + 2, l4 + r4 + 2);
            ans[3] = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 3, l4 + r4 + 1);
        } else {
            ans[0] = min(l1 + r1, l2 + r2 + 2, l3 + r3 + 2, l4 + r4 + 2);
            ans[1] = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 3, l4 + r4 + 1);
            ans[2] = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 1, l4 + r4 + 3);
            ans[3] = min(l1 + r1 + 2, l2 + r2, l3 + r3 + 2, l4 + r4 + 2);
        }
        return ans;
    }

    private int min(int... nums) {
        int ans = 1 << 30;
        for (int num : nums) {
            ans = Math.min(ans, num);
        }
        return ans;
    }
}