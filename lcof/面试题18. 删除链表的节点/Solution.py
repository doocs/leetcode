# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None
class Solution:
    def deleteNode(self, head: ListNode, val: int) -> ListNode:
        dummy = ListNode(0)
        dummy.next = head
        pre, p = dummy, head
        while p:
            if p.val == val:
                pre.next = p.next
                break
            pre, p = p, p.next
        return dummy.next
