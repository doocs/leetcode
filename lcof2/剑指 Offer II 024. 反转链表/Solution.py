# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def reverseList(self, head: ListNode) -> ListNode:
        pre, p = None, head
        while p:
            q = p.next
            p.next = pre
            pre = p
            p = q
        return pre
