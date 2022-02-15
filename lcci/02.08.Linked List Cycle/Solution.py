# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def detectCycle(self, head: ListNode) -> ListNode:
        slow = fast = head
        has_cycle = False
        while not has_cycle and fast and fast.next:
            slow, fast = slow.next, fast.next.next
            has_cycle = slow == fast
        if not has_cycle:
            return None
        p = head
        while p != slow:
            p, slow = p.next, slow.next
        return p
