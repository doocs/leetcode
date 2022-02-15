# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def getLonelyNodes(self, root: TreeNode) -> List[int]:
        def traverse(root):
            if root is None:
                return
            if root.left is None and root.right is not None:
                self.res.append(root.right.val)
            if root.left is not None and root.right is None:
                self.res.append(root.left.val)
            traverse(root.left)
            traverse(root.right)

        self.res = []
        traverse(root)
        return self.res
