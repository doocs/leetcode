class Solution:
    def countConsistent(self, nums: list[int]) -> int:
        n = len(nums)
        left = [-1] * n
        stk = []
        for i, x in enumerate(nums):
            while stk and (nums[stk[-1]] & x) == x:
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)

        stk = []
        ans = 0
        for i in range(n - 1, -1, -1):
            x = nums[i]
            while stk and nums[stk[-1]] != x and (nums[stk[-1]] & x) == x:
                stk.pop()
            right = stk[-1] if stk else n
            stk.append(i)
            ans += (i - left[i]) * (right - i)
        return ans
