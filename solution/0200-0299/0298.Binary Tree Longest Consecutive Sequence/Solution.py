# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def longestConsecutive(self, root: TreeNode) -> int:
        def dfs(root, p, t):
            nonlocal ans
            if root is None:
                return
            t = t + 1 if p is not None and p.val + 1 == root.val else 1
            ans = max(ans, t)
            dfs(root.left, root, t)
            dfs(root.right, root, t)

        ans = 1
        dfs(root, None, 1)
        return ans
