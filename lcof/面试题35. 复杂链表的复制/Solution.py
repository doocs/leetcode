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
        if not head:
            return None
        copy_head = Node(-1)
        cur, t = copy_head, head
        cache = {}
        while t:
            cur.next = Node(t.val)
            cache[t] = cur.next
            cur, t = cur.next, t.next
        t, cur = head, copy_head.next
        while t:
            cur.random = cache.get(t.random)
            cur, t = cur.next, t.next
        return copy_head.next
