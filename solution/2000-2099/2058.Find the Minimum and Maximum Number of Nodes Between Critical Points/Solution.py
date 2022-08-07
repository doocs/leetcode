# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def nodesBetweenCriticalPoints(self, head: Optional[ListNode]) -> List[int]:
        prev, curr = head, head.next
        first = last = None
        i = 1
        ans = [inf, -inf]
        while curr.next:
            if curr.val < min(prev.val, curr.next.val) or curr.val > max(
                prev.val, curr.next.val
            ):
                if last is None:
                    first = last = i
                else:
                    ans[0] = min(ans[0], i - last)
                    ans[1] = i - first
                    last = i
            i += 1
            prev, curr = curr, curr.next
        return ans if first != last else [-1, -1]
