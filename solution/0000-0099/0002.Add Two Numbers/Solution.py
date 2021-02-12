# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        carry = 0
        dummy = ListNode(-1)
        cur = dummy
        while l1 or l2 or carry:
            t = (0 if not l1 else l1.val) + (0 if not l2 else l2.val) + carry
            carry = t // 10
            cur.next = ListNode(t % 10)
            cur = cur.next
            l1 = None if not l1 else l1.next
            l2 = None if not l2 else l2.next
        return dummy.next
