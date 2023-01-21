"""
# Definition for a Node.
class Node:
    def __init__(self, val: int = 0, left: 'Node' = None, right: 'Node' = None, next: 'Node' = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next
"""


class Solution:
    def connect(self, root: "Node") -> "Node":
        def modify(curr):
            nonlocal prev, next
            if curr is None:
                return
            next = next or curr
            if prev:
                prev.next = curr
            prev = curr

        node = root
        while node:
            prev = next = None
            while node:
                modify(node.left)
                modify(node.right)
                node = node.next
            node = next
        return root
