public class Solution {
    private class Frame {
        public TreeNode node;
        public int state;

        public Frame(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public int[] FindMode(TreeNode root) {
        int mx = 0;
        int cnt = 0;
        int prev = 0;
        bool has = false;
        List<int> res = new List<int>();
        if (root != null) {
            Stack<Frame> stk = new Stack<Frame>();
            stk.Push(new Frame(root, 0));
            while (stk.Count > 0) {
                Frame cur = stk.Pop();
                TreeNode node = cur.node;
                if (cur.state == 0) {
                    stk.Push(new Frame(node, 1));
                    if (node.left != null) {
                        stk.Push(new Frame(node.left, 0));
                    }
                    continue;
                }
                cnt = has && prev == node.val ? cnt + 1 : 1;
                if (cnt > mx) {
                    res = new List<int>(new int[] { node.val });
                    mx = cnt;
                } else if (cnt == mx) {
                    res.Add(node.val);
                }
                prev = node.val;
                has = true;
                if (node.right != null) {
                    stk.Push(new Frame(node.right, 0));
                }
            }
        }
        int[] ans = new int[res.Count];
        for (int i = 0; i < res.Count; ++i) {
            ans[i] = res[i];
        }
        return ans;
    }
}
