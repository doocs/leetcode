# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isUnivalTree(self, root: TreeNode) -> bool:
        def dfs(node):
            if node is None:
                return True
            return node.val == root.val and dfs(node.left) and dfs(node.right)

        return dfs(root)
