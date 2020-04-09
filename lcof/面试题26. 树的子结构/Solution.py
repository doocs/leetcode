# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def isSubStructure(self, A: TreeNode, B: TreeNode) -> bool:
        return self.sub(A, B) if B else False

    def sub(self, A: TreeNode, B: TreeNode) -> bool:
        if B is None:
            return True
        if A is None:
            return False
        if A.val == B.val:
            return self.same(A, B) or self.sub(A.left, B) or self.sub(A.right, B)
        return self.sub(A.left, B) or self.sub(A.right, B)

    def same(self, A: TreeNode, B: TreeNode) -> bool:
        if B is None:
            return True
        if A is None or A.val != B.val:
            return False
        return self.same(A.left, B.left) and self.same(A.right, B.right)