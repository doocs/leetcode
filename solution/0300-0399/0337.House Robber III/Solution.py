# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def rob(self, root: TreeNode) -> int:
        @cache
        def dfs(root):
            if root is None:
                return 0
            if root.left is None and root.right is None:
                return root.val
            a = dfs(root.left) + dfs(root.right)
            b = root.val
            if root.left:
                b += dfs(root.left.left) + dfs(root.left.right)
            if root.right:
                b += dfs(root.right.left) + dfs(root.right.right)
            return max(a, b)

        return dfs(root)
