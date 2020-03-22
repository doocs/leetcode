# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    t, res = 0, -1

    def kthLargest(self, root: TreeNode, k: int) -> int:
        self.t = k
        self._traverse(root)
        return self.res

    def _traverse(self, node):
        if node:
            self._traverse(node.right)
            self.t -= 1
            if self.t == 0:
                self.res = node.val
                return
            self._traverse(node.left)
