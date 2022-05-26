# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:
        dummy = ListNode(0, head)
        p = q = dummy
        for i in range(n):
            p = p.next
        while p.next is not None:
            p = p.next
            q = q.next
        q.next = q.next.next
        return dummy.next