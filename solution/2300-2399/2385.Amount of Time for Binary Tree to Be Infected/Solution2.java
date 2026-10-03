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
    private static class Frame {
        TreeNode node;
        TreeNode fa;

        Frame(TreeNode node, TreeNode fa) {
            this.node = node;
            this.fa = fa;
        }
    }

    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        Deque<Frame> stk = new ArrayDeque<>();
        if (root != null) {
            stk.push(new Frame(root, null));
        }
        while (!stk.isEmpty()) {
            Frame cur = stk.pop();
            TreeNode node = cur.node;
            TreeNode fa = cur.fa;
            if (fa != null) {
                g.computeIfAbsent(node.val, k -> new ArrayList<>()).add(fa.val);
                g.computeIfAbsent(fa.val, k -> new ArrayList<>()).add(node.val);
            }
            if (node.right != null) {
                stk.push(new Frame(node.right, node));
            }
            if (node.left != null) {
                stk.push(new Frame(node.left, node));
            }
        }
        Map<Integer, Integer> dist = new HashMap<>();
        Deque<int[]> walk = new ArrayDeque<>();
        walk.push(new int[] {start, -1, 0});
        while (!walk.isEmpty()) {
            int[] cur = walk.pop();
            int node = cur[0], fa = cur[1], state = cur[2];
            List<Integer> nxts = g.getOrDefault(node, List.of());
            if (state == 0) {
                walk.push(new int[] {node, fa, 1});
                for (int i = nxts.size() - 1; i >= 0; --i) {
                    int nxt = nxts.get(i);
                    if (nxt != fa) {
                        walk.push(new int[] {nxt, node, 0});
                    }
                }
            } else {
                int best = 0;
                for (int nxt : nxts) {
                    if (nxt != fa) {
                        best = Math.max(best, 1 + dist.get(nxt));
                    }
                }
                dist.put(node, best);
            }
        }
        return dist.get(start);
    }
}
