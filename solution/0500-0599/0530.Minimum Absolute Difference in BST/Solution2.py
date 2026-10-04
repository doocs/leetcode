# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def getMinimumDifference(self, root: Optional[TreeNode]) -> int:
        pre = -inf
        ans = inf
        stk = [(root, 0)]
        while stk:
            node, state = stk.pop()
            if node is None:
                continue
            if state == 0:
                stk.append((node, 1))
                stk.append((node.left, 0))
                continue
            ans = min(ans, node.val - pre)
            pre = node.val
            stk.append((node.right, 0))
        return ans
