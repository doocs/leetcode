# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def isBalanced(self, root: TreeNode) -> bool:
        if root is None:
            return True
        return abs(self._height(root.left) - self._height(root.right)) <= 1 and self.isBalanced(root.left) and self.isBalanced(root.right)

    def _height(self, tree):
        if tree is None:
            return 0
        return 1 + max(self._height(tree.left), self._height(tree.right))