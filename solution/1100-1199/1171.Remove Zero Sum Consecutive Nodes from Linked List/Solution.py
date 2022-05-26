# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def removeZeroSumSublists(self, head: ListNode) -> ListNode:
        dummy = ListNode(0)
        dummy.next = head
        s, cur = 0, dummy
        pre_sum_node = {}
        while cur:
            s += cur.val
            pre_sum_node[s] = cur
            cur = cur.next
        s, cur = 0, dummy
        while cur:
            s += cur.val
            cur.next = pre_sum_node[s].next
            cur = cur.next
        return dummy.next
