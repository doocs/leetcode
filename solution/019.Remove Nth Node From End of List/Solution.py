# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def removeNthFromEnd(self, head, n):
        """
        :type head: ListNode
        :type n: int
        :rtype: ListNode
        """
        pre=ListNode(-1)
        pre.next=head
        fast=pre
        slow=pre
        
        for _ in range(n):
            fast=fast.next
        while fast.next:
            fast=fast.next
            slow=slow.next
        slow.next=slow.next.next
        return pre.next