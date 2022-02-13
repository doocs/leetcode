# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None
class Solution:
    def deleteNode(self, head: ListNode, val: int) -> ListNode:
        pre = dummy = ListNode(next=head)
        while pre.next and pre.next.val != val:
            pre = pre.next
        pre.next = None if not pre.next else pre.next.next
        return dummy.next
