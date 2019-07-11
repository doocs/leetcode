public class Solution {
    
    private TreeNode currentNode = null;
    private Stack<TreeNode> stack = new Stack<TreeNode>();

    public binarySearchTreeIterator(TreeNode root) {
        if (root != null) {
            currentNode = root;
        }
    }

    public boolean hasNext() {
        return currentNode != null || !stack.isEmpty();
    }

    public TreeNode next() {
        while (currentNode != null) {
            stack.push(currentNode);
            currentNode = currentNode.left;
        }

        currentNode = stack.pop();
        TreeNode node = currentNode;
        currentNode = currentNode.right;

        return node;
    }
    
}