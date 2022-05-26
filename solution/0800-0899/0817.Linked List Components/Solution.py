# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def numComponents(self, head: ListNode, nums: List[int]) -> int:
        s = set(nums)
        res, pre = 0, True
        while head:
            if head.val in s:
                if pre:
                    res += 1
                    pre = False
            else:
                pre = True
            head = head.next
        return res
