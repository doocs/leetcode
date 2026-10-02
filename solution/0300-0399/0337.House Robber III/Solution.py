# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def rob(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0
        order = []
        stack = [root]
        while stack:
            node = stack.pop()
            order.append(node)
            if node.left:
                stack.append(node.left)
            if node.right:
                stack.append(node.right)

        dp = {}
        for node in reversed(order):
            la, lb = dp.get(id(node.left), (0, 0))
            ra, rb = dp.get(id(node.right), (0, 0))
            dp[id(node)] = (node.val + lb + rb, max(la, lb) + max(ra, rb))
        return max(dp[id(root)])
