# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findBottomLeftValue(self, root: Optional[TreeNode]) -> int:
        def dfs(root, curr):
            if root is None:
                return
            dfs(root.left, curr + 1)
            dfs(root.right, curr + 1)
            nonlocal ans, mx
            if mx < curr:
                mx = curr
                ans = root.val

        ans = mx = 0
        dfs(root, 1)
        return ans
