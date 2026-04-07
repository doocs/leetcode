class Solution:
    def countGoodSubarrays(self, nums: list[int]) -> int:
        n = len(nums)
        l = [-1] * n
        stk = []
        for i, x in enumerate(nums):
            while stk and nums[stk[-1]] < x and (nums[stk[-1]] | x) == x:
                stk.pop()
            l[i] = stk[-1] if stk else -1
            stk.append(i)
        r = [n] * n
        stk = []
        for i in range(n - 1, -1, -1):
            while stk and (nums[stk[-1]] | nums[i]) == nums[i]:
                stk.pop()
            r[i] = stk[-1] if stk else n
            stk.append(i)
        return sum((i - l[i]) * (r[i] - i) for i in range(n))
