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
    private int ans;
    private int[] counter;

    public int pseudoPalindromicPaths (TreeNode root) {
        ans = 0;
        counter = new int[10];
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        ++counter[root.val];
        if (root.left == null && root.right == null) {
            if (check(counter)) {
                ++ans;
            }
        } else {
            dfs(root.left);
            dfs(root.right);
        }
        --counter[root.val];
    }

    private boolean check(int[] counter) {
        int n = 0;
        for (int i = 1; i < 10; ++i) {
            if (counter[i] % 2 == 1) {
                ++n;
            }
        }
        return n < 2;
    }
}