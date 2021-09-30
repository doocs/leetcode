class Solution:
    def nextGreaterElements(self, nums: List[int]) -> List[int]:
        n = len(nums)
        res = [-1] * n
        stk = []
        for i in range(n << 1):
            while stk and nums[stk[-1]] < nums[i % n]:
                res[stk.pop()] = nums[i % n]
            stk.append(i % n)
        return res
