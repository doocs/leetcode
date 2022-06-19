# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def getMinimumDifference(self, root: TreeNode) -> int:
        def dfs(root):
            if root is None:
                return
            dfs(root.left)
            nonlocal ans, prev
            ans = min(ans, abs(prev - root.val))
            prev = root.val
            dfs(root.right)

        ans = prev = inf
        dfs(root)
        return ans
