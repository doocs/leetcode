# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def rotateRight(self, head: ListNode, k: int) -> ListNode:
        if k == 0 or head is None or head.next is None:
            return head
        n, cur = 0, head
        while cur:
            n, cur = n + 1, cur.next
        k %= n
        if k == 0:
            return head

        slow = fast = head
        for _ in range(k):
            fast = fast.next
        while fast.next:
            slow, fast = slow.next, fast.next

        start = slow.next
        slow.next = None
        fast.next = head
        return start
