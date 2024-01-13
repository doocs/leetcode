class Solution:
    def validSubarrays(self, nums: List[int]) -> int:
        n = len(nums)
        stk = []
        ans = 0
        for i in range(n - 1, -1, -1):
            while stk and nums[stk[-1]] >= nums[i]:
                stk.pop()
            ans += (stk[-1] if stk else n) - i
            stk.append(i)
        return ans
