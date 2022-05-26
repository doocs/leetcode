"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, next=None):
        self.val = val
        self.next = next
"""


class Solution:
    def insert(self, head: 'Node', insertVal: int) -> 'Node':
        node = Node(insertVal)
        if head is None:
            node.next = node
            return node
        p = head
        while True:
            if (
                p.val <= insertVal
                and insertVal <= p.next.val
                or p.val > p.next.val
                and (insertVal <= p.next.val or insertVal >= p.val)
                or p.next == head
            ):

                node.next = p.next
                p.next = node
                break

            p = p.next
        return head
