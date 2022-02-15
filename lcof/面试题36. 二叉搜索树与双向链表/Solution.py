"""
# Definition for a Node.
class Node:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
"""


class Solution:
    def treeToDoublyList(self, root: 'Node') -> 'Node':
        def dfs(cur):
            if cur is None:
                return
            dfs(cur.left)
            if self.pre is None:
                self.head = cur
            else:
                self.pre.right = cur
            cur.left = self.pre
            self.pre = cur
            dfs(cur.right)

        if root is None:
            return None
        self.head = self.pre = None
        dfs(root)
        self.head.left = self.pre
        self.pre.right = self.head
        return self.head
