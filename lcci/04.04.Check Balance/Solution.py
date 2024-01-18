# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def isBalanced(self, root: TreeNode) -> bool:
        def dfs(root: TreeNode):
            if root is None:
                return 0, True
            a, b = dfs(root.left)
            c, d = dfs(root.right)
            return max(a, c) + 1, abs(a - c) <= 1 and b and d

        return dfs(root)[1]
