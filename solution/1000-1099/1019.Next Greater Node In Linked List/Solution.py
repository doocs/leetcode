# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def nextLargerNodes(self, head: ListNode) -> List[int]:
        nums = []
        while head:
            nums.append(head.val)
            head = head.next
        s = []
        larger = [0] * len(nums)
        for i, num in enumerate(nums):
            while s and nums[s[-1]] < num:
                larger[s.pop()] = num
            s.append(i)
        return larger
