# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def longestUnivaluePath(self, root: TreeNode) -> int:
        res = 0

        def dfs(root):
            nonlocal res
            if root is None:
                return 0
            left, right = dfs(root.left), dfs(root.right)

            l = r = 0
            if root.left and root.left.val == root.val:
                l = left + 1
            if root.right and root.right.val == root.val:
                r = right + 1
            res = max(res, l + r)
            return max(l, r)

        dfs(root)
        return res
