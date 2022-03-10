# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def mergeNodes(self, head: Optional[ListNode]) -> Optional[ListNode]:
        dummy = tail = ListNode()
        s = 0
        cur = head.next
        while cur:
            if cur.val != 0:
                s += cur.val
            else:
                tail.next = ListNode(s)
                tail = tail.next
                s = 0
            cur = cur.next
        return dummy.next
