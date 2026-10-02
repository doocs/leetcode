# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def sumNumbers(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0
        ans = 0
        stack = [(root, root.val)]
        while stack:
            node, value = stack.pop()
            if node.left is None and node.right is None:
                ans += value
                continue
            if node.right:
                stack.append((node.right, value * 10 + node.right.val))
            if node.left:
                stack.append((node.left, value * 10 + node.left.val))
        return ans
