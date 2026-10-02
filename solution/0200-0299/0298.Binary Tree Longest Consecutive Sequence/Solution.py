# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def longestConsecutive(self, root: Optional[TreeNode]) -> int:
        ans = 0
        if root is None:
            return ans
        stack = [(root, False)]
        lengths = {}
        while stack:
            node, visited = stack.pop()
            if not visited:
                stack.append((node, True))
                if node.right:
                    stack.append((node.right, False))
                if node.left:
                    stack.append((node.left, False))
                continue
            left = lengths.get(id(node.left), 0) if node.left and node.left.val - node.val == 1 else 0
            right = lengths.get(id(node.right), 0) if node.right and node.right.val - node.val == 1 else 0
            lengths[id(node)] = max(left, right) + 1
            ans = max(ans, lengths[id(node)])
        return ans
