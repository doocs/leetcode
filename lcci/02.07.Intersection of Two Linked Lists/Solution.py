# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def getIntersectionNode(self, headA: ListNode, headB: ListNode) -> ListNode:
        len1, len2 = self._length(headA), self._length(headB)
        if len1 < len2:
            headA, headB = headB, headA
        differ = abs(len1 - len2)
        for _ in range(differ):
            headA = headA.next
        while headA:
            if headA == headB:
                return headA
            headA = headA.next
            headB = headB.next
        return None

    def _length(self, node: ListNode) -> int:
        n = 0
        while node:
            node = node.next
            n += 1
        return n