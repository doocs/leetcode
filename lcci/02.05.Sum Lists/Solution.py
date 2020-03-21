# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        p = ListNode(-1)
        carry, t = 0, p
        while l1 or l2:
            s = (0 if l1 is None else l1.val) + \
                (0 if l2 is None else l2.val) + carry
            carry = 1 if s > 9 else 0
            t.next = ListNode(s % 10)
            t = t.next
            l1 = l1.next if l1 else l1
            l2 = l2.next if l2 else l2
        t.next = None if carry == 0 else ListNode(carry)
        return p.next
