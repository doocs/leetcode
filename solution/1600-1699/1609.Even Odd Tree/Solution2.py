# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isEvenOddTree(self, root: Optional[TreeNode]) -> bool:
        def dfs(root, i):
            if root is None:
                return True
            even = i % 2 == 0
            prev = d.get(i, 0 if even else inf)
            if even and (root.val % 2 == 0 or prev >= root.val):
                return False
            if not even and (root.val % 2 == 1 or prev <= root.val):
                return False
            d[i] = root.val
            return dfs(root.left, i + 1) and dfs(root.right, i + 1)

        d = {}
        return dfs(root, 0)
