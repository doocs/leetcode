# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxPathSum(self, root: TreeNode) -> int:
        ans = -inf

        def dfs(node: TreeNode) -> int:
            if not node:
                return 0
            left = max(0, dfs(node.left))
            right = max(0, dfs(node.right))
            nonlocal ans
            ans = max(ans, node.val + left + right)
            return node.val + max(left, right)

        dfs(root)
        return ans
