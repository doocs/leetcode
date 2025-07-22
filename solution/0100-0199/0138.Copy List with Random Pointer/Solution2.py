"""
# Definition for a Node.
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random
"""


class Solution:
    def copyRandomList(self, head: "Optional[Node]") -> "Optional[Node]":
        if head is None:
            return None
        cur = head
        while cur:
            node = Node(cur.val, cur.next)
            cur.next = node
            cur = node.next
        cur = head
        while cur:
            cur.next.random = cur.random.next if cur.random else None
            cur = cur.next.next
        cur = head
        ans = head.next
        while cur.next:
            node = cur.next
            cur.next = node.next
            cur = node
        return ans
