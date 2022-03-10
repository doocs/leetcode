"""
# Definition for a Node.
class Node:
    def __init__(self, val, prev, next, child):
        self.val = val
        self.prev = prev
        self.next = next
        self.child = child
"""


class Solution:
    def flatten(self, head: 'Node') -> 'Node':
        if head is None:
            return None
        dummy = Node()
        tail = dummy

        def preOrder(node: 'Node'):
            nonlocal tail
            if node is None:
                return
            next = node.next
            child = node.child
            tail.next = node
            node.prev = tail
            tail = tail.next
            node.child = None
            preOrder(child)
            preOrder(next)

        preOrder(head)
        dummy.next.prev = None
        return dummy.next
