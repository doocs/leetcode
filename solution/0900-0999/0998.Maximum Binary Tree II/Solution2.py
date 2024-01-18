# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def insertIntoMaxTree(
        self, root: Optional[TreeNode], val: int
    ) -> Optional[TreeNode]:
        if root.val < val:
            return TreeNode(val, root)
        curr = root
        node = TreeNode(val)
        while curr.right and curr.right.val > val:
            curr = curr.right
        node.left = curr.right
        curr.right = node
        return root
