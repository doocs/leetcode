# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    first = None
    second = None
    prev = None

    def recoverTree(self, root: TreeNode) -> None:
        """
        Do not return anything, modify root in-place instead.
        """
        def dfs(root):
            if root:
                dfs(root.left)
                if self.prev and root.val < self.prev.val:
                    if not self.first:
                        self.first = self.prev
                    self.second = root
                self.prev = root
                dfs(root.right)
        dfs(root)
        self.first.val, self.second.val = self.second.val, self.first.val
