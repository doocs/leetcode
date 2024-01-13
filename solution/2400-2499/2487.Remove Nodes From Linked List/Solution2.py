# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def removeNodes(self, head: Optional[ListNode]) -> Optional[ListNode]:
        dummy = ListNode(inf, head)
        cur = head
        stk = [dummy]
        while cur:
            while stk[-1].val < cur.val:
                stk.pop()
            stk[-1].next = cur
            stk.append(cur)
            cur = cur.next
        return dummy.next
