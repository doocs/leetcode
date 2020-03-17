# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def searchBST(self, root, val):
        """
        :type root: TreeNode
        :type val: int
        :rtype: TreeNode
        """

        temp = root

        while temp != None :

            if temp.val == val :
                return temp
            elif val < temp.val :
                temp = temp.left
            else:
                temp = temp.right

        return None
