public class Solution {

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

        private TreeNode currentNode = null;
        private Stack<TreeNode> stack = new Stack<TreeNode>();

        public BSTIterator(TreeNode root) {
            if (root != null) {
                currentNode = root;
            }
        }

        /** @return the next smallest number */
        public int next() {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            currentNode = stack.pop();
            TreeNode node = currentNode;
            currentNode = currentNode.right;

            return node.val;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return currentNode != null || !stack.isEmpty();
        }
    }

    /**
     * Your BSTIterator object will be instantiated and called as such:
     * BSTIterator obj = new BSTIterator(root);
     * int param_1 = obj.next();
     * boolean param_2 = obj.hasNext();
     */
    
}
