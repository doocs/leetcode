# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findTarget(self, root: Optional[TreeNode], k: int) -> bool:
        vis = set()
        stk = []
        if root is not None:
            stk.append(root)
        while stk:
            node = stk.pop()
            if k - node.val in vis:
                return True
            vis.add(node.val)
            if node.right is not None:
                stk.append(node.right)
            if node.left is not None:
                stk.append(node.left)
        return False
