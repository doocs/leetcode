# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def swapPairs(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if (not head) or (not head.next):
            return head
        pre=head.next
        p=head
        q=head.next
        
        while q:
            t=q.next
            q.next=p
            if (not t) or (not t.next):
                p.next=t
                break
            p.next=t.next
            p=t
            q=p.next
        return pre