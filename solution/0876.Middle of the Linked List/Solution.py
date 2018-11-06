# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def middleNode(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if not head:
            return None
        if not head.next:
            return head
        fast=head
        slow=head
        while fast.next:
            fast=fast.next.next
            slow=slow.next
            if not fast or not fast.next:
                return slow