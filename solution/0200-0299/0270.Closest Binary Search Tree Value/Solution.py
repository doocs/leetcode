# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def closestValue(self, root: Optional[TreeNode], target: float) -> int:
        def dfs(root):
            if root is None:
                return
            dfs(root.left)
            nonlocal ans, mi
            t = abs(root.val - target)
            if t < mi:
                mi = t
                ans = root.val
            dfs(root.right)

        ans, mi = root.val, inf
        dfs(root)
        return ans
