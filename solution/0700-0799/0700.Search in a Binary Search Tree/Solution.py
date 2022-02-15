# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def searchBST(self, root: TreeNode, val: int) -> TreeNode:
        if root is None or root.val == val:
            return root
        return (
            self.searchBST(root.right, val)
            if root.val < val
            else self.searchBST(root.left, val)
        )
