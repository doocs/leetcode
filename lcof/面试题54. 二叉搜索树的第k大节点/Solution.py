# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def kthLargest(self, root: TreeNode, k: int) -> int:
        def inorder(root):
            if root is None:
                return
            inorder(root.right)
            self.cur -= 1
            if self.cur == 0:
                self.res = root.val
                return
            inorder(root.left)

        self.cur = k
        inorder(root)
        return self.res
