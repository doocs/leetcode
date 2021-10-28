/**
 * Definition for a binary tree node.
 * class Node {
 *     char val;
 *     Node left;
 *     Node right;
 *     Node() {this.val = ' ';}
 *     Node(char val) { this.val = val; }
 *     Node(char val, Node left, Node right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean checkEquivalence(Node root1, Node root2) {
        int[] ans1 = dfs(root1);
        int[] ans2 = dfs(root2);
        for (int i = 0; i < 26; ++i) {
            if (ans1[i] != ans2[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] dfs(Node root) {
        int[] ans = new int[26];
        if (root == null) {
            return ans;
        }
        if (root.val == '+' || root.val == '-') {
            int[] left = dfs(root.left);
            int[] right = dfs(root.right);
            calc(ans, left, right, root.val);
        } else {
            ++ans[root.val - 'a'];
        }
        return ans;
    }

    private void calc(int[] ans, int[] left, int[] right, char op) {
        for (int i = 0; i < 26; ++i) {
            ans[i] = op == '+' ? left[i] + right[i] : left[i] - right[i];
        }
    }
}