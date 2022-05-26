# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def inorderSuccessor(self, root: 'TreeNode', p: 'TreeNode') -> 'TreeNode':
        cur, ans = root, None
        while cur:
            if cur.val <= p.val:
                cur = cur.right
            else:
                ans = cur
                cur = cur.left
        return ans
