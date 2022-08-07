# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isValidBST(self, root: TreeNode) -> bool:
        def dfs(root):
            nonlocal prev
            if root is None:
                return True
            if not dfs(root.left):
                return False
            if prev >= root.val:
                return False
            prev = root.val
            if not dfs(root.right):
                return False
            return True

        prev = -inf
        return dfs(root)
