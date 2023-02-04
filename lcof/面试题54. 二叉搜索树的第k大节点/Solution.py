# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def kthLargest(self, root: TreeNode, k: int) -> int:
        def dfs(root):
            nonlocal k, ans
            if root is None or k == 0:
                return
            dfs(root.right)
            k -= 1
            if k == 0:
                ans = root.val
            dfs(root.left)

        ans = 0
        dfs(root)
        return ans
