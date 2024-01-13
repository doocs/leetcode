# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def evaluateTree(self, root: Optional[TreeNode]) -> bool:
        if root.left is None:
            return bool(root.val)
        l = self.evaluateTree(root.left)
        r = self.evaluateTree(root.right)
        return l or r if root.val == 2 else l and r
