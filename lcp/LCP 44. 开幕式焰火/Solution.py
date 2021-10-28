# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
class Solution:
    def numColor(self, root: TreeNode) -> int:
        s = set()

        def dfs(root):
            if root:
                s.add(root.val)
                dfs(root.left)
                dfs(root.right)

        dfs(root)
        return len(s)
