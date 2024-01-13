# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isValidBST(self, root: Optional[TreeNode]) -> bool:
        def dfs(root, l, r):
            if root is None:
                return True
            if root.val <= l or root.val >= r:
                return False
            return dfs(root.left, l, root.val) and dfs(root.right, root.val, r)

        return dfs(root, -inf, inf)
