# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def isSubStructure(self, A: TreeNode, B: TreeNode) -> bool:
        def sub(A, B):
            if B is None:
                return True
            if A is None:
                return False
            return A.val == B.val and sub(A.left, B.left) and sub(A.right, B.right)
        if B is None or A is None:
            return False
        if A.val != B.val:
            return self.isSubStructure(A.left, B) or self.isSubStructure(A.right, B)
        return sub(A, B) or self.isSubStructure(A.left, B) or self.isSubStructure(A.right, B)
