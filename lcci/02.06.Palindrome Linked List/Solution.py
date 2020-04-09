# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def isPalindrome(self, head: ListNode) -> bool:
        if head is None or head.next is None:
            return True
        slow, fast = head, head.next
        while fast and fast.next:
            fast = fast.next.next
            slow = slow.next
        cur = slow.next
        slow.next = None
        while cur:
            t = cur.next
            cur.next = slow.next
            slow.next = cur
            cur = t
        t = slow.next
        while t:
            if head.val != t.val:
                return False
            t = t.next
            head = head.next
        return True