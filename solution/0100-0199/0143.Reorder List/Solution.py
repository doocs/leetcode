# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reorderList(self, head: ListNode) -> None:
        """
        Do not return anything, modify head in-place instead.
        """
        if head is None or head.next is None:
            return
        slow, fast = head, head.next
        while fast and fast.next:
            slow, fast = slow.next, fast.next.next
        cur = slow.next
        slow.next = None
        pre = None
        while cur:
            t = cur.next
            cur.next = pre
            pre = cur
            cur = t
        cur = head
        while pre:
            t1 = cur.next
            cur.next = pre
            cur = t1
            t2 = pre.next
            pre.next = t1
            pre = t2
