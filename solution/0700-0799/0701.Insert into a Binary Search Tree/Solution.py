# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def insertIntoBST(self, root: TreeNode, val: int) -> TreeNode:
        def dfs(root):
            if root is None:
                return TreeNode(val)
            if root.val < val:
                root.right = dfs(root.right)
            else:
                root.left = dfs(root.left)
            return root

        return dfs(root)
