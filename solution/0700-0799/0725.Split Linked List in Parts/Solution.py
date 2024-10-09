# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def splitListToParts(
        self, head: Optional[ListNode], k: int
    ) -> List[Optional[ListNode]]:
        n = 0
        cur = head
        while cur:
            n += 1
            cur = cur.next
        cnt, mod = divmod(n, k)
        ans = [None] * k
        cur = head
        for i in range(k):
            if cur is None:
                break
            ans[i] = cur
            m = cnt + int(i < mod)
            for _ in range(1, m):
                cur = cur.next
            nxt = cur.next
            cur.next = None
            cur = nxt
        return ans
