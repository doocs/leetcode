# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isCousins(self, root: Optional[TreeNode], x: int, y: int) -> bool:
        def dfs(root, fa, d):
            if root is None:
                return
            if root.val == x:
                t[0] = (fa, d)
            if root.val == y:
                t[1] = (fa, d)
            dfs(root.left, root, d + 1)
            dfs(root.right, root, d + 1)

        t = [None, None]
        dfs(root, None, 0)
        return t[0][0] != t[1][0] and t[0][1] == t[1][1]
