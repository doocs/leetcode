# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def isBalanced(self, root: TreeNode) -> bool:
        if not root:
            return True
        l, r = self._height(root.left), self._height(root.right)
        return (
            abs(l - r) < 2
            and self.isBalanced(root.left)
            and self.isBalanced(root.right)
        )

    def _height(self, node):
        if not node:
            return 0
        return 1 + max(self._height(node.left), self._height(node.right))
