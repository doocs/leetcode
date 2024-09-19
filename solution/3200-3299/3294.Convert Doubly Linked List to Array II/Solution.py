"""
# Definition for a Node.
class Node:
    def __init__(self, val, prev=None, next=None):
        self.val = val
        self.prev = prev
        self.next = next
"""


class Solution:
    def toArray(self, node: "Optional[Node]") -> List[int]:
        cur = node
        while cur and cur.prev:
            cur = cur.prev
        ans = []
        while cur:
            ans.append(cur.val)
            cur = cur.next
        return ans
