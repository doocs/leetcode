# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def minimumFlips(self, root: Optional[TreeNode], result: bool) -> int:
        def dfs(root: Optional[TreeNode]) -> (int, int):
            if root is None:
                return inf, inf
            x = root.val
            if x in (0, 1):
                return x, x ^ 1
            l, r = dfs(root.left), dfs(root.right)
            if x == 2:
                return l[0] + r[0], min(l[0] + r[1], l[1] + r[0], l[1] + r[1])
            if x == 3:
                return min(l[0] + r[0], l[0] + r[1], l[1] + r[0]), l[1] + r[1]
            if x == 4:
                return min(l[0] + r[0], l[1] + r[1]), min(l[0] + r[1], l[1] + r[0])
            return min(l[1], r[1]), min(l[0], r[0])

        return dfs(root)[int(result)]
