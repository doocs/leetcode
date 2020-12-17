# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def reverseBetween(self, head: ListNode, m: int, n: int) -> ListNode:
        if head is None or head.next is None or m == n:
            return head
        dummy = ListNode(0)
        dummy.next = head
        pre, cur = dummy, head
        for _ in range(m - 1):
            pre = cur
            cur = cur.next
        p, q = pre, cur
        for _ in range(n - m + 1):
            t = cur.next
            cur.next = pre
            pre = cur
            cur = t
        p.next = pre
        q.next = cur
        return dummy.next
