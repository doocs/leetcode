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
    private List<Long> arr = new ArrayList<>();

    public long kthLargestLevelSum(TreeNode root, int k) {
        dfs(root, 0);
        if (arr.size() < k) {
            return -1;
        }
        Collections.sort(arr, Collections.reverseOrder());
        return arr.get(k - 1);
    }

    private void dfs(TreeNode root, int d) {
        if (root == null) {
            return;
        }
        if (arr.size() <= d) {
            arr.add(0L);
        }
        arr.set(d, arr.get(d) + root.val);
        dfs(root.left, d + 1);
        dfs(root.right, d + 1);
    }
}