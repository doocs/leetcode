# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def getIntersectionNode(self, headA: ListNode, headB: ListNode) -> ListNode:
        if headA is None or headB is None:
            return None
        len1 = len2 = 0
        p, q = headA, headB
        while p:
            p = p.next
            len1 += 1
        while q:
            q = q.next
            len2 += 1
        p, q = headA, headB
        if len1 > len2:
            p, q = q, p
        for _ in range(abs(len1 - len2)):
            q = q.next
        while p and q:
            if p == q:
                return p
            p = p.next
            q = q.next
            
