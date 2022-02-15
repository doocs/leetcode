# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def removeDuplicateNodes(self, head: ListNode) -> ListNode:
        if head is None or head.next is None:
            return head
        cache = set()
        cache.add(head.val)
        cur, p = head, head.next
        while p:
            if p.val not in cache:
                cur.next = p
                cur = cur.next
                cache.add(p.val)
            p = p.next
        cur.next = None
        return head
