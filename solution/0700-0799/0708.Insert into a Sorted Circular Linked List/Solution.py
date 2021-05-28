"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, next=None):
        self.val = val
        self.next = next
"""

class Solution:
    def insert(self, head: 'Node', insertVal: int) -> 'Node':
        node = Node(val=insertVal)
        if head is None:
            node.next = node
            return node
        pre, cur = head, head.next
        while 1:
            if pre.val <= insertVal <= cur.val or (pre.val > cur.val and (insertVal >= pre.val or insertVal <= cur.val)):
                break
            pre, cur = cur, cur.next
            if pre == head:
                break
        pre.next = node
        node.next = cur
        return head
