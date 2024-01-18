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
        int[] cnt1 = dfs(root1);
        int[] cnt2 = dfs(root2);
        for (int i = 0; i < 26; ++i) {
            if (cnt1[i] != cnt2[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] dfs(Node root) {
        int[] cnt = new int[26];
        if (root == null) {
            return cnt;
        }
        if (root.val == '+' || root.val == '-') {
            int[] l = dfs(root.left);
            int[] r = dfs(root.right);
            int k = root.val == '+' ? 1 : -1;
            for (int i = 0; i < 26; ++i) {
                cnt[i] += l[i] + r[i] * k;
            }
        } else {
            cnt[root.val - 'a']++;
        }
        return cnt;
    }
}