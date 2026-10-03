# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def longestZigZag(self, root: TreeNode) -> int:
        ans = 0
        stk = [(root, 0, 0)]
        while stk:
            node, l, r = stk.pop()
            if node is None:
                continue
            ans = max(ans, l, r)
            stk.append((node.right, 0, l + 1))
            stk.append((node.left, r + 1, 0))
        return ans
