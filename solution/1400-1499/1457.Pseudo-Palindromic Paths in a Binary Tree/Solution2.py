# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def pseudoPalindromicPaths(self, root: Optional[TreeNode]) -> int:
        ans = 0
        stk = [(root, 0)]
        while stk:
            node, mask = stk.pop()
            if node is None:
                continue
            mask ^= 1 << node.val
            if node.left is None and node.right is None:
                ans += (mask & (mask - 1)) == 0
            else:
                stk.append((node.right, mask))
                stk.append((node.left, mask))
        return ans
