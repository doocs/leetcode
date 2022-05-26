# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def longestUnivaluePath(self, root: TreeNode) -> int:
        def dfs(root):
            if root is None:
                return 0
            left, right = dfs(root.left), dfs(root.right)
            left = left + 1 if root.left and root.left.val == root.val else 0
            right = right + 1 if root.right and root.right.val == root.val else 0
            nonlocal ans
            ans = max(ans, left + right)
            return max(left, right)

        ans = 0
        dfs(root)
        return ans
