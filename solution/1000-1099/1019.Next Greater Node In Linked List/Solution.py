# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def nextLargerNodes(self, head: Optional[ListNode]) -> List[int]:
        nums = []
        while head:
            nums.append(head.val)
            head = head.next
        stk = []
        n = len(nums)
        ans = [0] * n
        for i in range(n - 1, -1, -1):
            while stk and stk[-1] <= nums[i]:
                stk.pop()
            if stk:
                ans[i] = stk[-1]
            stk.append(nums[i])
        return ans
