# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def buildTree(self, inorder: List[int], postorder: List[int]) -> TreeNode:
        def build(inorder, postorder, i1, i2, p1, p2):
            if i1 > i2 or p1 > p2:
                return None
            root_val = postorder[p2]
            pos = -1
            for i in range(i1, i2 + 1):
                if inorder[i] == root_val:
                    pos = i
                    break
            root = TreeNode(root_val)
            root.left = None if pos == i1 else build(inorder, postorder, i1, pos - 1, p1, p1 - i1 + pos - 1)
            root.right = None if pos == i2 else build(inorder, postorder, pos + 1, i2, p1 - i1 + pos, p2 - 1)
            return root
        return build(inorder, postorder, 0, len(inorder) - 1, 0, len(postorder) - 1)