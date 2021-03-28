# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:
        dummy = ListNode(next=head)
        p = q = dummy
        for i in range(n):
            p = p.next
        while p.next:
            p, q = p.next, q.next
        q.next = q.next.next
        return dummy.next
