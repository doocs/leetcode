# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def replaceValueInTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        root.val = 0
        q = [root]
        while q:
            t = []
            s = 0
            for node in q:
                if node.left:
                    t.append(node.left)
                    s += node.left.val
                if node.right:
                    t.append(node.right)
                    s += node.right.val
            for node in q:
                sub = (node.left.val if node.left else 0) + (
                    node.right.val if node.right else 0
                )
                if node.left:
                    node.left.val = s - sub
                if node.right:
                    node.right.val = s - sub
            q = t
        return root
