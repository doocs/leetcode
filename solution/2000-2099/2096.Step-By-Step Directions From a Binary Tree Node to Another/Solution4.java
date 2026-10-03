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
        StringBuilder pathToStart = new StringBuilder();
        StringBuilder pathToDest = new StringBuilder();
        dfs(root, startValue, pathToStart);
        dfs(root, destValue, pathToDest);
        int i = 0;
        while (i < pathToStart.length() && i < pathToDest.length()
            && pathToStart.charAt(i) == pathToDest.charAt(i)) {
            ++i;
        }
        return "U".repeat(pathToStart.length() - i) + pathToDest.substring(i);
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
