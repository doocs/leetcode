# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

# 递归版本
"""
class Solution:
    def inorderTraversal(self, root):
        """
        : type root: TreeNode
        : rtype: List[int]
        """
        def dp(node):
            if not node:
                return
            dp(node.left)
            ls.append(node.val)
            dp(node.right)
        ls=[]
        dp(root)
        return ls
"""

# 非递归版本
class Solution:
    def inorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        ls = []
        stack = []
        while root or stack:
            while root:
                stack.append(root)
                root = root.left
            if stack:
                root = stack.pop(-1)
                ls.append(root.val)
                root = root.right
        return ls
