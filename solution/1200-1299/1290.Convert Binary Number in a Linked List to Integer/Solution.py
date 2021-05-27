# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def getDecimalValue(self, head: ListNode) -> int:
        n, cur = 0, head
        while cur:
            n += 1
            cur = cur.next
        res, cur = 0, head
        while cur:
            n -= 1
            if cur.val == 1:
                res += (2 ** (n))
            cur = cur.next
        return res
