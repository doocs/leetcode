"""
# Definition for a Node.
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random
"""


class Solution:
    def copyRandomList(self, head: "Node") -> "Node":
        d = {}
        dummy = tail = Node(0)
        cur = head
        while cur:
            tail.next = Node(cur.val)
            tail = tail.next
            d[cur] = tail
            cur = cur.next
        tail = dummy.next
        cur = head
        while cur:
            tail.random = d.get(cur.random)
            tail = tail.next
            cur = cur.next
        return dummy.next
