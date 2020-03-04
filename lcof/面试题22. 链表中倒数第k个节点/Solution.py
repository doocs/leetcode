# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def getKthFromEnd(self, head: ListNode, k: int) -> ListNode:
        if not (head or head.next):
            return head
        
        p = q = head
        for _ in range(k):
            p = p.next
        while p:
            p = p.next
            q = q.next
        return q
