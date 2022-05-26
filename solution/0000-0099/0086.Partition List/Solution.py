# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def partition(self, head, x):
        """
        :type head: ListNode
        :type x: int
        :rtype: ListNode
        """
        leftDummy=ListNode(-1)
        rightDummy=ListNode(-1)
        leftCur=leftDummy
        rightCur=rightDummy              
        
        while head:
            if head.val<x:
                leftCur.next=head
                leftCur=leftCur.next
            else:
                rightCur.next=head
                rightCur=rightCur.next
            head=head.next
            
        leftCur.next=rightDummy.next
        rightCur.next=None
        return leftDummy.next