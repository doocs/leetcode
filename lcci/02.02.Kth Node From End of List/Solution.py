# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def kthToLast(self, head: ListNode, k: int) -> int:
        p = q = head
        for _ in range(k):
            q = q.next
        while q:
            p, q = p.next, q.next
        return p.val