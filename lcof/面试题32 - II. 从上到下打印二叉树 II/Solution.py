# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

from queue import Queue

class Solution:
    def levelOrder(self, root: TreeNode) -> List[List[int]]:
        if root is None:
            return []
        q = Queue()
        q.put(root)
        cnt = 1
        res = []
        while not q.empty():
            t = []
            num = 0
            for _ in range(cnt):
                node = q.get()
                t.append(node.val)
                if node.left:
                    q.put(node.left)
                    num += 1
                if node.right:
                    q.put(node.right)
                    num += 1
            res.append(t)
            cnt = num
        return res