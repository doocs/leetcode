# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def rotateRight(self, head: ListNode, k: int) -> ListNode:
        if head is None or head.next is None or k == 0:
            return head
        n = 0
        cur = head
        while cur:
            n += 1
            cur = cur.next
        k %= n
        if k == 0:
            return head
        p = q = head
        for i in range(k):
            q = q.next
        while q.next:
            p, q = p.next, q.next
        start = p.next
        p.next = None
        q.next = head
        return start
