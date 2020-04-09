# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def deleteNode(self, head: ListNode, val: int) -> ListNode:
        pre = ListNode(0)
        pre.next = head
        dummy = pre
        p = head
        while p:
            if p.val == val:
                pre.next = p.next
                break
            else:
                pre, p = p, p.next
        return dummy.next
