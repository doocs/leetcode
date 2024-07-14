# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None
class Solution:
    def isPalindrome(self, head: ListNode) -> bool:
        if head is None:
            return True
        slow, fast = head, head.next
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
        p = slow.next
        slow.next = None
        dummy = ListNode()
        while p:
            next = p.next
            p.next = dummy.next
            dummy.next = p
            p = next
        p = dummy.next
        while p:
            if head.val != p.val:
                return False
            head = head.next
            p = p.next
        return True
