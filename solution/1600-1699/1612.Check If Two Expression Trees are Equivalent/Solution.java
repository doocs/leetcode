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
    private int[] cnt = new int[26];

    public boolean checkEquivalence(Node root1, Node root2) {
        dfs(root1, 1);
        dfs(root2, -1);
        for (int x : cnt) {
            if (x != 0) {
                return false;
            }
        }
        return true;
    }

    private void dfs(Node root, int v) {
        if (root == null) {
            return;
        }
        if (root.val != '+') {
            cnt[root.val - 'a'] += v;
        }
        dfs(root.left, v);
        dfs(root.right, v);
    }
}