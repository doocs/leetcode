# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def largestBSTSubtree(self, root: Optional[TreeNode]) -> int:
        ans = 0
        order = []
        stack = [root] if root else []
        while stack:
            node = stack.pop()
            order.append(node)
            if node.left:
                stack.append(node.left)
            if node.right:
                stack.append(node.right)

        dp = {}
        for node in reversed(order):
            lmi, lmx, ln = dp.get(id(node.left), (inf, -inf, 0))
            rmi, rmx, rn = dp.get(id(node.right), (inf, -inf, 0))
            if lmx < node.val < rmi:
                size = ln + rn + 1
                ans = max(ans, size)
                dp[id(node)] = (min(lmi, node.val), max(rmx, node.val), size)
            else:
                dp[id(node)] = (-inf, inf, 0)
        return ans
