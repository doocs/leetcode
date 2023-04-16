# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def replaceValueInTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        def dfs1(root, d):
            if root is None:
                return
            if len(s) <= d:
                s.append(0)
            s[d] += root.val
            dfs1(root.left, d + 1)
            dfs1(root.right, d + 1)

        def dfs2(root, d):
            if root is None:
                return
            t = (root.left.val if root.left else 0) + (
                root.right.val if root.right else 0
            )
            if root.left:
                root.left.val = s[d] - t
            if root.right:
                root.right.val = s[d] - t
            dfs2(root.left, d + 1)
            dfs2(root.right, d + 1)

        s = []
        dfs1(root, 0)
        root.val = 0
        dfs2(root, 1)
        return root
