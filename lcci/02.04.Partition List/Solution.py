# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def partition(self, head: ListNode, x: int) -> ListNode:
        left, right = ListNode(0), ListNode(0)
        p1, p2 = left, right
        while head:
            if head.val < x:
                p1.next = head
                p1 = p1.next
            else:
                p2.next = head
                p2 = p2.next
            head = head.next
        p1.next = right.next
        p2.next = None
        return left.next
