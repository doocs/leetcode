# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def swapPairs(self, head: ListNode) -> ListNode:
        dummy = ListNode(next=head)
        pre, cur = dummy, head
        while cur and cur.next:
            pre.next = cur.next
            t = cur.next.next
            cur.next.next = cur
            cur.next = t
            pre = cur
            cur = cur.next
        return dummy.next
