# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def mergeTwoLists(self, l1: ListNode, l2: ListNode) -> ListNode:
        if l1 is None or l2 is None:
            return l1 or l2
        head = ListNode(0)
        p = head
        while l1 and l2:
            if l1.val < l2.val:
                t = l1.next
                p.next = l1
                p = l1
                l1 = t
            else:
                t = l2.next
                p.next = l2
                p = l2
                l2 = t

        p.next = l1 or l2
        return head.next