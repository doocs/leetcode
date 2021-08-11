# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def postorderTraversal(self, root: TreeNode) -> List[int]:
        res = []
        while root:
            if root.right is None:
                res.append(root.val)
                root = root.left
            else:
                next = root.right
                while next.left and next.left != root:
                    next = next.left
                if next.left is None:
                    res.append(root.val)
                    next.left = root
                    root = root.right
                else:
                    next.left = None
                    root = root.left
        return res[::-1]
