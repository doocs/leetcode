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
        if root.left is None and root.right is None:
            return 1
        l = self.minDepth(root.left)
        r = self.minDepth(root.right)
        # 如果左子树和右子树其中一个为空，那么需要返回比较大的那个子树的深度
        if root.left is None or root.right is None:
            return l + r + 1
        # 左右子树都不为空，返回最小深度+1即可
        return min(l, r) + 1
