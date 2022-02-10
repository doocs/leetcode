# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def reverseList(self, head: ListNode) -> ListNode:
        previous, current, next = None, head, None
        
        while current is not None:
            next = current.next
            current.next = previous
            previous = current
            current = next
        
        return previous
