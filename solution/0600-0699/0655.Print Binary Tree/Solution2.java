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
    public List<List<String>> printTree(TreeNode root) {
        int h = height(root);
        int m = h + 1, n = (1 << (h + 1)) - 1;
        String[][] res = new String[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(res[i], "");
        }
        Deque<Tuple> q = new ArrayDeque<>();
        q.offer(new Tuple(root, 0, (n - 1) / 2));
        while (!q.isEmpty()) {
            Tuple p = q.pollFirst();
            root = p.node;
            int r = p.r, c = p.c;
            res[r][c] = String.valueOf(root.val);
            if (root.left != null) {
                q.offer(new Tuple(root.left, r + 1, c - (1 << (h - r - 1))));
            }
            if (root.right != null) {
                q.offer(new Tuple(root.right, r + 1, c + (1 << (h - r - 1))));
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for (String[] t : res) {
            ans.add(Arrays.asList(t));
        }
        return ans;
    }

    private int height(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int h = -1;
        while (!q.isEmpty()) {
            ++h;
            for (int n = q.size(); n > 0; --n) {
                root = q.pollFirst();
                if (root.left != null) {
                    q.offer(root.left);
                }
                if (root.right != null) {
                    q.offer(root.right);
                }
            }
        }
        return h;
    }
}

class Tuple {
    TreeNode node;
    int r;
    int c;

    public Tuple(TreeNode node, int r, int c) {
        this.node = node;
        this.r = r;
        this.c = c;
    }
}