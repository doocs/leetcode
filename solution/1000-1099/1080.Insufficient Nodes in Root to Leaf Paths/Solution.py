class Solution:
    def sufficientSubset(self, root: TreeNode, limit: int) -> TreeNode:
        if root is None:
            return None

        limit -= root.val
        if root.left is None and root.right is None:
            return None if limit > 0 else root

        root.left = self.sufficientSubset(root.left, limit)
        root.right = self.sufficientSubset(root.right, limit)

        if root.left is None and root.right is None:
            return None
        return root
