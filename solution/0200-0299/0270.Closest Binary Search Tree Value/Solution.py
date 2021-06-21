# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def closestValue(self, root: TreeNode, target: float) -> int:
        res, min_diff = root.val, float('inf')
        while root:
            val = abs(root.val - target)
            if min_diff > val:
                min_diff = val
                res = root.val
            if root.val > target:
                root = root.left
            else:
                root = root.right
        return res
