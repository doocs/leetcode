# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def addOneRow(self, root: TreeNode, val: int, depth: int) -> TreeNode:
        def dfs(root, d):
            if root is None:
                return
            if d == depth - 1:
                l = TreeNode(val=val, left=root.left)
                r = TreeNode(val=val, right=root.right)
                root.left, root.right = l, r
                return
            dfs(root.left, d + 1)
            dfs(root.right, d + 1)

        if depth == 1:
            return TreeNode(val=val, left=root)
        dfs(root, 1)
        return root
