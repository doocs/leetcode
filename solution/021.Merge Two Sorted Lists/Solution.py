# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def mergeTwoLists(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        while l1 and l2:
            if l1.val < l2.val:
                l1.next=self.mergeTwoLists(l1.next,l2)
                return l1
            else:
                l2.next=self.mergeTwoLists(l1,l2.next)
                return l2
        return l1 or l2