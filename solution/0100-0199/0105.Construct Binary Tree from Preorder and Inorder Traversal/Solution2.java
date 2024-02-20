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
    private List<Integer> preorder;
    private Map<Integer, List<Integer>> d = new HashMap<>();

    public List<TreeNode> getBinaryTrees(List<Integer> preOrder, List<Integer> inOrder) {
        int n = preOrder.size();
        this.preorder = preOrder;
        for (int i = 0; i < n; ++i) {
            d.computeIfAbsent(inOrder.get(i), k -> new ArrayList<>()).add(i);
        }
        return dfs(0, 0, n);
    }

    private List<TreeNode> dfs(int i, int j, int n) {
        List<TreeNode> ans = new ArrayList<>();
        if (n <= 0) {
            ans.add(null);
            return ans;
        }
        int v = preorder.get(i);
        for (int k : d.get(v)) {
            if (k >= j && k < j + n) {
                for (TreeNode l : dfs(i + 1, j, k - j)) {
                    for (TreeNode r : dfs(i + 1 + k - j, k + 1, n - 1 - (k - j))) {
                        ans.add(new TreeNode(v, l, r));
                    }
                }
            }
        }
        return ans;
    }
}