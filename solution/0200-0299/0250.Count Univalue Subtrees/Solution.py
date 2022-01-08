# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def countUnivalSubtrees(self, root: Optional[TreeNode]) -> int:
        def dfs(root):
            if root is None:
                return True
            left, right = dfs(root.left), dfs(root.right)
            t = True
            if root.left and root.left.val != root.val:
                t = False
            if root.right and root.right.val != root.val:
                t = False
            nonlocal ans
            if left and t and right:
                ans += 1
            return left and t and right

        ans = 0
        dfs(root)
        return ans
