# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def isSymmetric(self, root: TreeNode) -> bool:
        def is_symmetric(left, right):
            if left is None and right is None:
                return True
            if left is None or right is None or left.val != right.val:
                return False
            return is_symmetric(left.left, right.right) and is_symmetric(
                left.right, right.left
            )

        if root is None:
            return True
        return is_symmetric(root.left, root.right)
