# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isCousins(self, root: Optional[TreeNode], x: int, y: int) -> bool:
        def dfs(root, parent, depth):
            if root is None:
                return
            if root.val == x:
                st[0] = (parent, depth)
            elif root.val == y:
                st[1] = (parent, depth)
            dfs(root.left, root, depth + 1)
            dfs(root.right, root, depth + 1)

        st = [None, None]
        dfs(root, None, 0)
        return st[0][0] != st[1][0] and st[0][1] == st[1][1]
