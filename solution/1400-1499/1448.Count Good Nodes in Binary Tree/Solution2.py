# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def goodNodes(self, root: TreeNode) -> int:
        ans = 0
        stk = [(root, -1000000)]
        while stk:
            node, mx = stk.pop()
            if node is None:
                continue
            if mx <= node.val:
                ans += 1
                mx = node.val
            stk.append((node.right, mx))
            stk.append((node.left, mx))
        return ans
