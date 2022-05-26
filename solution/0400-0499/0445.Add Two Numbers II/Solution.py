# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        s1, s2 = [], []
        while l1:
            s1.append(l1.val)
            l1 = l1.next
        while l2:
            s2.append(l2.val)
            l2 = l2.next
        carry, dummy = 0, ListNode()
        while s1 or s2 or carry:
            carry += (0 if not s1 else s1.pop()) + (0 if not s2 else s2.pop())
            carry, val = divmod(carry, 10)
            node = ListNode(val, dummy.next)
            dummy.next = node
        return dummy.next
