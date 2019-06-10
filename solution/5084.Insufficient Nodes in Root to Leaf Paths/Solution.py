'''
对于每一个node选一条最大的路径，若其和小于limit，则该节点应该被删除。
'''

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def sufficientSubset(self, root, limit: int) -> TreeNode:
        self.findAns(root, limit, 0)
        if root.val is None:
            return None
        self.trever(root)
        return root

    def findAns(self, node, limit, presum):
        '''
        标记需要删除的节点
        '''
        if node is None:
            return 0
        left = self.findAns(node.left, limit, presum + node.val)
        right = self.findAns(node.right, limit, presum + node.val)
        ret = presum + node.val + max(left, right)
        if ret < limit:
            node.val = None
        return ret - presum

    def trever(self, node):
        '''
        删除节点
        '''
        if node.left is not None:
            if node.left.val is None:
                node.left = None
            else:
                self.trever(node.left)
        if node.right is not None:
            if node.right.val is None:
                node.right = None
            else:
                self.trever(node.right)
