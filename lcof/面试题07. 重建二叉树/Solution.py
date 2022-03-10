# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
        if not preorder:
            return None
        v = preorder[0]
        root = TreeNode(val=v)
        i = inorder.index(v)
        root.left = self.buildTree(preorder[1 : 1 + i], inorder[:i])
        root.right = self.buildTree(preorder[1 + i :], inorder[i + 1 :])
        return root
