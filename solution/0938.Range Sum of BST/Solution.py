# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def rangeSumBST(self, root, L, R):
        """
        :type root: TreeNode
        :type L: int
        :type R: int
        :rtype: int
        """
        def searchBST(node):
            if not node:
                return
            if L <= node.val <= R:
                self.ans += node.val
                searchBST(node.right)
                searchBST(node.left)
            elif node.val < L:
                searchBST(node.right)
            elif node.val > R:
                searchBST(node.left)
        self.ans = 0
        searchBST(root)
        return self.ans
