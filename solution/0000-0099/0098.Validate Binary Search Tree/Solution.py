# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    pre = None
    def isValidBST(self, root: TreeNode) -> bool:
        if not root:
            return True
        if not self.isValidBST(root.left):
            return False
        if self.pre is not None and self.pre >= root.val:
            return False
        self.pre = root.val
        if not self.isValidBST(root.right):
            return False
        return True
