# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def twoSumBSTs(self, root1: TreeNode, root2: TreeNode, target: int) -> bool:
        vals1, vals2 = [], []

        def inorder(root, vals):
            if root:
                inorder(root.left, vals)
                vals.append(root.val)
                inorder(root.right, vals)

        inorder(root1, vals1)
        inorder(root2, vals2)

        i, j = 0, len(vals2) - 1
        while i < len(vals1) and j >= 0:
            if vals1[i] + vals2[j] == target:
                return True
            if vals1[i] + vals2[j] < target:
                i += 1
            else:
                j -= 1
        return False
