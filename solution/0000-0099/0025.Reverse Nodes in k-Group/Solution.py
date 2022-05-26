# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseKGroup(self, head: ListNode, k: int) -> ListNode:
        def reverseList(head):
            pre, p = None, head
            while p:
                q = p.next
                p.next = pre
                pre = p
                p = q
            return pre

        dummy = ListNode(next=head)
        pre = cur = dummy
        while cur.next:
            for _ in range(k):
                cur = cur.next
                if cur is None:
                    return dummy.next
            t = cur.next
            cur.next = None
            start = pre.next
            pre.next = reverseList(start)
            start.next = t
            pre = start
            cur = pre
        return dummy.next
