/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class BSTIterator {

    Stack<TreeNode> vector = new Stack<>();
    TreeNode current;

    public BSTIterator(TreeNode root) {
        current = root;
        // 一直放入左儿子（左）
        while (current != null) {
            vector.push(current);
            current = current.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !vector.isEmpty() || current != null;
    }

    /** @return the next smallest number */
    public int next() {
        // 一直放入左儿子（左）
        while (current != null) {
            vector.push(current);
            current = current.left;
        }
        int ans = 0;
        // 访问当前元素（中），把右儿子入栈（右）
        if (!vector.isEmpty()) {
            current = vector.pop();
            ans = current.val;
            current = current.right;
        }
        return ans;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */