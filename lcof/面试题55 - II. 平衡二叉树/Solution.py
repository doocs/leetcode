# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def isBalanced(self, root: TreeNode) -> bool:
        def height(root):
            if root is None:
                return 0
            return 1 + max(height(root.left), height(root.right))

        if root is None:
            return True
        return (
            abs(height(root.left) - height(root.right)) <= 1
            and self.isBalanced(root.left)
            and self.isBalanced(root.right)
        )
