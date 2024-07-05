# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def nodesBetweenCriticalPoints(self, head: Optional[ListNode]) -> List[int]:
        ans = [inf, -inf]
        first = last = -1
        i = 0
        while head.next.next:
            a, b, c = head.val, head.next.val, head.next.next.val
            if a > b < c or a < b > c:
                if last == -1:
                    first = last = i
                else:
                    ans[0] = min(ans[0], i - last)
                    last = i
                    ans[1] = max(ans[1], last - first)
            i += 1
            head = head.next
        return [-1, -1] if first == last else ans
