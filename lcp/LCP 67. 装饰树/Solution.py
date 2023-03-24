# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def expandBinaryTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        def dfs(root):
            if root is None:
                return None
            l, r = dfs(root.left), dfs(root.right)
            if l:
                root.left = TreeNode(-1, l)
            if r:
                root.right = TreeNode(-1, None, r)
            return root

        return dfs(root)
