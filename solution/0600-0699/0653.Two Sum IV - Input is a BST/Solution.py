# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findTarget(self, root: TreeNode, k: int) -> bool:
        def find(root):
            if not root:
                return False
            if k - root.val in nodes:
                return True
            nodes.add(root.val)
            return find(root.left) or find(root.right)

        nodes = set()
        return find(root)
