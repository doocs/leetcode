# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def deepestLeavesSum(self, root: Optional[TreeNode]) -> int:
        def dfs(root, i):
            nonlocal ans, mx
            if root is None:
                return
            if i == mx:
                ans += root.val
            elif i > mx:
                ans = root.val
                mx = i
            dfs(root.left, i + 1)
            dfs(root.right, i + 1)

        ans = mx = 0
        dfs(root, 1)
        return ans
