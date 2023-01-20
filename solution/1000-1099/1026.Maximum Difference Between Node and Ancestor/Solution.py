# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxAncestorDiff(self, root: Optional[TreeNode]) -> int:
        def dfs(root, mi, mx):
            if root is None:
                return
            nonlocal ans
            ans = max(ans, abs(mi - root.val), abs(mx - root.val))
            mi = min(mi, root.val)
            mx = max(mx, root.val)
            dfs(root.left, mi, mx)
            dfs(root.right, mi, mx)

        ans = 0
        dfs(root, root.val, root.val)
        return ans
