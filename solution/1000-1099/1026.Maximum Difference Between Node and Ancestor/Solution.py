# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxAncestorDiff(self, root: TreeNode) -> int:
        def dfs(root, mx, mi):
            if root is None:
                return
            nonlocal ans
            ans = max(ans, abs(root.val - mx), abs(root.val - mi))
            mx = max(mx, root.val)
            mi = min(mi, root.val)
            dfs(root.left, mx, mi)
            dfs(root.right, mx, mi)

        ans = 0
        dfs(root, root.val, root.val)
        return ans
