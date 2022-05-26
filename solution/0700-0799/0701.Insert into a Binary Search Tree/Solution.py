# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

# 解法一
class Solution:
    def insertIntoBST(self, root, val):
        """
        :type root: TreeNode
        :type val: int
        :rtype: TreeNode
        """
        if not root:
            return TreeNode(val)
        if root.val < val:
            if root.right:
                self.insertIntoBST(root.right, val)
            else:
                root.right = TreeNode(val)
        if root.val > val:
            if root.left:
                self.insertIntoBST(root.left, val)
            else:
                root.left = TreeNode(val)
        return root


"""
# 解法二
class Solution:
    def insertIntoBST(self, root, val):
        """
        : type root: TreeNode
        : type val: int
        : rtype: TreeNode
        """
        if not root:
            return TreeNode(val)
        elif root.left is None:
            root.left = TreeNode(root.val)
            root.val = val
        root.val, val = val, root.val
        node = root.left
        while node.right:
            node = node.right
        node.right = TreeNode(val)
        return root
"""
