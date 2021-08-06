# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isUnivalTree(self, root: TreeNode) -> bool:
        def dfs(root):
            if root is None:
                return True
            if root.val != self.val:
                return False
            return dfs(root.left) and dfs(root.right)

        self.val = root.val
        return dfs(root)
