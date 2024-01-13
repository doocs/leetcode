# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def reversePrint(self, head: ListNode) -> List[int]:
        if head is None:
            return []
        ans = self.reversePrint(head.next)
        ans.append(head.val)
        return ans
