# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def recoverTree(self, root: Optional[TreeNode]) -> None:
        """
        Do not return anything, modify root in-place instead.
        """

        def dfs(root):
            nonlocal prev, first, second
            if root:
                dfs(root.left)
                if prev:
                    if first is None and root.val < prev.val:
                        first = prev
                    if first and root.val < prev.val:
                        second = root
                prev = root
                dfs(root.right)

        prev = first = second = None
        dfs(root)
        first.val, second.val = second.val, first.val
