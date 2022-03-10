"""
# Definition for a Node.
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random
"""


class Solution:
    def copyRandomList(self, head: 'Node') -> 'Node':
        if head is None:
            return None

        cur = head
        while cur:
            node = Node(cur.val, cur.next)
            cur.next = node
            cur = node.next

        cur = head
        while cur:
            cur.next.random = None if cur.random is None else cur.random.next
            cur = cur.next.next

        copy = head.next
        cur = head
        while cur:
            next = cur.next
            cur.next = next.next
            next.next = None if next.next is None else next.next.next
            cur = cur.next
        return copy
