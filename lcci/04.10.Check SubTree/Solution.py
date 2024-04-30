# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def checkSubTree(self, t1: TreeNode, t2: TreeNode) -> bool:
        def dfs(t1, t2):
            if t2 is None:
                return t1 is None
            if t1 is None or t1.val != t2.val:
                return False
            return dfs(t1.left, t2.left) and dfs(t1.right, t2.right)

        if t2 is None:
            return True
        if t1 is None:
            return False
        if dfs(t1, t2):
            return True
        return self.checkSubTree(t1.left, t2) or self.checkSubTree(t1.right, t2)
