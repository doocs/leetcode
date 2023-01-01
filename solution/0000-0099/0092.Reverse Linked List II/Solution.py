# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseBetween(
        self, head: Optional[ListNode], left: int, right: int
    ) -> Optional[ListNode]:
        if head.next is None or left == right:
            return head
        dummy = ListNode(0, head)
        pre = dummy
        for _ in range(left - 1):
            pre = pre.next
        p, q = pre, pre.next
        cur = q
        for _ in range(right - left + 1):
            t = cur.next
            cur.next = pre
            pre, cur = cur, t
        p.next = pre
        q.next = cur
        return dummy.next
