# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def minDepth(self, root: TreeNode) -> int:
        if root is None:
            return 0
        # 如果左子树和右子树其中一个为空，那么需要返回比较大的那个子树的深度+1
        if root.left is None:
            return 1 + self.minDepth(root.right)
        if root.right is None:
            return 1 + self.minDepth(root.left)
        # 左右子树都不为空，返回最小深度+1即可
        return 1 + min(self.minDepth(root.left), self.minDepth(root.right))
