# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
        if preorder is None or inorder is None or len(preorder) != len(inorder):
            return None
        return self._build_tree(preorder, 0, len(preorder) - 1, inorder, 0, len(inorder) - 1)
        
    def _build_tree(self, preorder, s1, e1, inorder, s2, e2):
        if s1 > e1 or s2 > e2:
            return None
        index = self._find_index(inorder, s2, e2, preorder[s1])
        tree = TreeNode(preorder[s1])
        tree.left = self._build_tree(preorder, s1 + 1, index + s1 - s2, inorder, s2, index - 1)
        tree.right = self._build_tree(preorder, index + s1 - s2 + 1, e1, inorder, index + 1, e2)
        return tree

    def _find_index(self, order, s, e, val):
        for i in range(s, e + 1):
            if order[i] == val:
                return i
        return -1