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
            if not cur:
                return
            dfs(cur.left)
            cur.left = self.lastNode
            if self.lastNode:
                self.lastNode.right = cur
            self.lastNode = cur
            dfs(cur.right)

        if not root:
            return root
        self.lastNode = head = Node(-1)
        dfs(root)
        head = head.right
        head.left = self.lastNode
        self.lastNode.right = head
        return head
