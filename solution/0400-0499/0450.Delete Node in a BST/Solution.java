class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            TreeNode newRoot = root.right;
            TreeNode parent = null;
            while (newRoot.left != null) {
                parent = newRoot;
                newRoot = newRoot.left;
            }
            if (parent != null) {
                parent.left = newRoot.right;
                newRoot.right = root.right;
            }
            newRoot.left = root.left;
            return newRoot;
        }
    }
}
