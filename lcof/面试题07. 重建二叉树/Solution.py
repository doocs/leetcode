# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
        def build(preorder, inorder, p1, p2, i1, i2):
            if p1 > p2 or i1 > i2:
                return None
            root_val = preorder[p1]
            pos = i1
            while pos <= i2:
                if inorder[pos] == root_val:
                    break
                pos += 1
            node = TreeNode(root_val)
            node.left = None if pos == i1 else build(preorder, inorder, p1 + 1, pos - i1 + p1, i1, pos - 1)
            node.right = None if pos == i2 else build(preorder, inorder, pos - i1 + p1 + 1, p2, pos + 1, i2)
            return node
        return build(preorder, inorder, 0, len(preorder) - 1, 0, len(inorder) - 1)
