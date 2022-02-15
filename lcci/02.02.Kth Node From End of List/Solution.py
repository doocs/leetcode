# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def kthToLast(self, head: ListNode, k: int) -> int:
        slow = fast = head
        for _ in range(k):
            fast = fast.next
        while fast:
            slow, fast = slow.next, fast.next
        return slow.val
