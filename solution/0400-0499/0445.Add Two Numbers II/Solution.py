# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def addTwoNumbers(
        self, l1: Optional[ListNode], l2: Optional[ListNode]
    ) -> Optional[ListNode]:
        s1, s2 = [], []
        while l1:
            s1.append(l1.val)
            l1 = l1.next
        while l2:
            s2.append(l2.val)
            l2 = l2.next
        dummy = ListNode()
        carry = 0
        while s1 or s2 or carry:
            s = (0 if not s1 else s1.pop()) + (0 if not s2 else s2.pop()) + carry
            carry, val = divmod(s, 10)
            # node = ListNode(val, dummy.next)
            # dummy.next = node
            dummy.next = ListNode(val, dummy.next)
        return dummy.next
