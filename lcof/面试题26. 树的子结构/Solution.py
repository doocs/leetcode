# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def isSubStructure(self, A: TreeNode, B: TreeNode) -> bool:
        def dfs(A, B):
            if B is None:
                return True
            if A is None or A.val != B.val:
                return False
            return dfs(A.left, B.left) and dfs(A.right, B.right)

        if A is None or B is None:
            return False
        if dfs(A, B):
            return True
        return self.isSubStructure(A.left, B) or self.isSubStructure(A.right, B)
