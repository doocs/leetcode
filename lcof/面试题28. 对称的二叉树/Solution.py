# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def isSymmetric(self, root: TreeNode) -> bool:
        if root is None:
            return True
        return self.symmetric(root.left, root.right)
    
    def symmetric(self, node1, node2) -> bool:
        if node1 is None and node2 is None:
            return True
        if node1 is None or node2 is None or node1.val != node2.val:
            return False
        return self.symmetric(node1.left, node2.right) and self.symmetric(node1.right, node2.left)