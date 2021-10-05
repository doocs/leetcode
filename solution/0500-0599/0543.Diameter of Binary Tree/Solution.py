# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def diameterOfBinaryTree(self, root: TreeNode) -> int:
        res = 0

        def dfs(root):
            nonlocal res
            if root is None:
                return 0
            left, right = dfs(root.left), dfs(root.right)
            res = max(res, left + right)
            return 1 + max(left, right)
        
        dfs(root)
        return res
