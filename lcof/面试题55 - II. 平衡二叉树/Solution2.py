# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def isBalanced(self, root: TreeNode) -> bool:
        def dfs(root):
            if root is None:
                return (True, 0)
            l, ld = dfs(root.left)
            r, rd = dfs(root.right)
            d = max(ld, rd) + 1
            if l and r and abs(ld - rd) <= 1:
                return (True, d)
            return (False, d)

        return dfs(root)[0]
