# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def minCameraCover(self, root: TreeNode) -> int:
        def dfs(root):
            nonlocal ans
            if root is None:
                return 2
            left, right = dfs(root.left), dfs(root.right)
            if left == 0 or right == 0:
                ans += 1
                return 1
            return 2 if left == 1 or right == 1 else 0

        ans = 0
        return (dfs(root) == 0) + ans
