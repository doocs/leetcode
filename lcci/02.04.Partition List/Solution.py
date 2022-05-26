# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def partition(self, head: ListNode, x: int) -> ListNode:
        if head is None or head.next is None:
            return head
        left, right = ListNode(-1), ListNode(-1)
        p, q = left, right
        while head:
            t = head.next
            head.next = None
            if head.val < x:
                p.next = head
                p = p.next
            else:
                q.next = head
                q = q.next
            head = t
        p.next = right.next
        return left.next
