# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findTilt(self, root: TreeNode) -> int:
        ans = 0

        def sum(root):
            if root is None:
                return 0
            nonlocal ans
            left = sum(root.left)
            right = sum(root.right)
            ans += abs(left - right)
            return root.val + left + right

        sum(root)
        return ans
