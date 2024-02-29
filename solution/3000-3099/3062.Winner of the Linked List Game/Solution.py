# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def gameResult(self, head: Optional[ListNode]) -> str:
        odd = even = 0
        while head:
            a = head.val
            b = head.next.val
            odd += a < b
            even += a > b
            head = head.next.next
        if odd > even:
            return "Odd"
        if odd < even:
            return "Even"
        return "Tie"
