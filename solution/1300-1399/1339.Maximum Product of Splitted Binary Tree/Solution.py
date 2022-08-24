# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxProduct(self, root: Optional[TreeNode]) -> int:
        def sum(root):
            if root is None:
                return 0
            return root.val + sum(root.left) + sum(root.right)

        def dfs(root):
            nonlocal s, ans
            if root is None:
                return 0
            t = root.val + dfs(root.left) + dfs(root.right)
            if t < s:
                ans = max(ans, t * (s - t))
            return t

        s = sum(root)
        ans = 0
        dfs(root)
        ans %= 10**9 + 7
        return ans
