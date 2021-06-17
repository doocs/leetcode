# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findSecondMinimumValue(self, root: TreeNode) -> int:
        def traverse(root, val):
            if root is None:
                return
            traverse(root.left, val)
            traverse(root.right, val)
            if root.val > val:
                self.res = root.val if self.res == -1 else min(self.res, root.val)

        self.res = -1
        traverse(root, root.val)
        return self.res
