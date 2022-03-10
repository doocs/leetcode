# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        dummy = cur = ListNode(0)
        carry = 0
        while l1 or l2 or carry:
            carry += (0 if not l1 else l1.val) + (0 if not l2 else l2.val)
            cur.next = ListNode(carry % 10)
            cur = cur.next
            carry //= 10
            l1 = None if not l1 else l1.next
            l2 = None if not l2 else l2.next
        return dummy.next
