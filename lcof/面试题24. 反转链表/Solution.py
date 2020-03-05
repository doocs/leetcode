# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def reverseList(self, head: ListNode) -> ListNode:
        if not head:
            return head
        dummy = ListNode(0)
        p = head
        while p:
            q = p.next
            p.next = dummy.next
            dummy.next = p
            p = q
        return dummy.next