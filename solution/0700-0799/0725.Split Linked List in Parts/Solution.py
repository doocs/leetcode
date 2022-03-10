# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def splitListToParts(self, root: ListNode, k: int) -> List[ListNode]:
        n, cur = 0, root
        while cur:
            n += 1
            cur = cur.next
        cur = root
        width, remainder = divmod(n, k)
        res = [None for _ in range(k)]
        for i in range(k):
            head = cur
            for j in range(width + (i < remainder) - 1):
                if cur:
                    cur = cur.next
            if cur:
                cur.next, cur = None, cur.next
            res[i] = head
        return res
