# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def removeDuplicateNodes(self, head: ListNode) -> ListNode:
        vis = set()
        pre = ListNode(0, head)
        while pre.next:
            if pre.next.val in vis:
                pre.next = pre.next.next
            else:
                vis.add(pre.next.val)
                pre = pre.next
        return head
