# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        ans = -inf
        gains = {}
        stack = [(root, False)]
        while stack:
            node, visited = stack.pop()
            if node is None:
                continue
            if visited:
                left = max(0, gains.get(id(node.left), 0))
                right = max(0, gains.get(id(node.right), 0))
                ans = max(ans, node.val + left + right)
                gains[id(node)] = node.val + max(left, right)
            else:
                stack.append((node, True))
                stack.append((node.right, False))
                stack.append((node.left, False))
        return ans
