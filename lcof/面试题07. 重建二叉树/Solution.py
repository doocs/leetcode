# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    indexes = {}
    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
        def build(preorder, inorder, p1, p2, i1, i2) -> TreeNode:
            if p1 > p2 or i1 > i2:
                return None
            root_val = preorder[p1]
            pos = self.indexes[root_val]
            root = TreeNode(root_val)
            root.left = None if pos == i1 else build(preorder, inorder, p1 + 1, p1 - i1 + pos, i1, pos - 1)
            root.right = None if pos == i2 else build(preorder, inorder, p1 - i1 + pos + 1, p2, pos + 1, i2)
            return root
        n = len(inorder)
        for i in range(n):
            self.indexes[inorder[i]] = i
        return build(preorder, inorder, 0, n - 1, 0, n - 1)
