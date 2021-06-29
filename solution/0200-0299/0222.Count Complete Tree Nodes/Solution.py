# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def countNodes(self, root: TreeNode) -> int:
        def depth(root):
            res = 0
            while root:
                res += 1
                root = root.left
            return res

        if root is None:
            return 0
        left_depth = depth(root.left)
        right_depth = depth(root.right)
        if left_depth > right_depth:
            return (1 << right_depth) + self.countNodes(root.left)
        return (1 << left_depth) + self.countNodes(root.right)
