class Solution {
    private static class Frame {
        TreeNode node;
        int state;

        Frame(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public String getDirections(TreeNode root, int startValue, int destValue) {
        TreeNode node = lca(root, startValue, destValue);
        StringBuilder pathToStart = new StringBuilder();
        StringBuilder pathToDest = new StringBuilder();
        dfs(node, startValue, pathToStart);
        dfs(node, destValue, pathToDest);
        return "U".repeat(pathToStart.length()) + pathToDest.toString();
    }

    private TreeNode lca(TreeNode root, int p, int q) {
        Map<TreeNode, TreeNode> ret = new IdentityHashMap<>();
        Deque<Frame> stk = new ArrayDeque<>();
        stk.push(new Frame(root, 0));
        while (!stk.isEmpty()) {
            Frame cur = stk.pop();
            TreeNode node = cur.node;
            if (cur.state == 0) {
                if (node == null) {
                    continue;
                }
                if (node.val == p || node.val == q) {
                    ret.put(node, node);
                    continue;
                }
                stk.push(new Frame(node, 1));
                stk.push(new Frame(node.right, 0));
                stk.push(new Frame(node.left, 0));
            } else {
                TreeNode left = node.left == null ? null : ret.get(node.left);
                TreeNode right = node.right == null ? null : ret.get(node.right);
                if (left != null && right != null) {
                    ret.put(node, node);
                } else {
                    ret.put(node, left != null ? left : right);
                }
            }
        }
        return ret.get(root);
    }

    private boolean dfs(TreeNode start, int x, StringBuilder path) {
        Deque<Frame> stk = new ArrayDeque<>();
        stk.push(new Frame(start, 0));
        while (!stk.isEmpty()) {
            Frame cur = stk.pop();
            TreeNode node = cur.node;
            if (cur.state == 0) {
                if (node == null) {
                    continue;
                }
                if (node.val == x) {
                    return true;
                }
                path.append('L');
                stk.push(new Frame(node, 1));
                stk.push(new Frame(node.left, 0));
            } else if (cur.state == 1) {
                path.setCharAt(path.length() - 1, 'R');
                stk.push(new Frame(node, 2));
                stk.push(new Frame(node.right, 0));
            } else {
                path.deleteCharAt(path.length() - 1);
            }
        }
        return false;
    }
}
