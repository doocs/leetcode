# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def sumEvenGrandparent(self, root: TreeNode) -> int:
        self.res = 0

        def dfs(g, p):
            if p is None:
                return
            if g.val % 2 == 0:
                if p.left:
                    self.res += p.left.val
                if p.right:
                    self.res += p.right.val
            dfs(p, p.left)
            dfs(p, p.right)

        dfs(root, root.left)
        dfs(root, root.right)
        return self.res
