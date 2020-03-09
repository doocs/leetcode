# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

from queue import Queue

class Solution:
    def levelOrder(self, root: TreeNode) -> List[int]:
        if root is None:
            return []
        s = Queue()
        res = []
        s.put(root)
        while not s.empty():
            node = s.get()
            res.append(node.val)
            if node.left:
                s.put(node.left)
            if node.right:
                s.put(node.right)
        return res            