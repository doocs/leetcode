# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def balanceBST(self, root: TreeNode) -> TreeNode:
        def dfs(root):
            if root is None:
                return
            dfs(root.left)
            vals.append(root.val)
            dfs(root.right)

        def build(i, j):
            if i > j:
                return None
            mid = (i + j) >> 1
            root = TreeNode(vals[mid])
            root.left = build(i, mid - 1)
            root.right = build(mid + 1, j)
            return root

        vals = []
        dfs(root)
        return build(0, len(vals) - 1)
