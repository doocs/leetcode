# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def evaluateTree(self, root: Optional[TreeNode]) -> bool:
        def dfs(root):
            if root.left is None and root.right is None:
                return bool(root.val)
            l, r = dfs(root.left), dfs(root.right)
            return (l or r) if root.val == 2 else (l and r)

        return dfs(root)
