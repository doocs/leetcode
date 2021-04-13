# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def minDiffInBST(self, root: TreeNode) -> int:
        def inorder(root):
            if not root:
                return
            inorder(root.left)
            if self.pre is not None:
                self.min_diff = min(self.min_diff, abs(root.val - self.pre))
            self.pre = root.val
            inorder(root.right)
        
        self.pre = None
        self.min_diff = 10 ** 5
        inorder(root)
        return self.min_diff
