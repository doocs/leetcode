# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def getDecimalValue(self, head: ListNode) -> int:
        res = 0
        while head:
            res = (res << 1) + head.val
            head = head.next
        return res
