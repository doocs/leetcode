class Solution:
    def waysToSplitArray(self, nums: List[int]) -> int:
        s = sum(nums)
        ans = t = 0
        for v in nums[:-1]:
            t += v
            if t >= s - t:
                ans += 1
        return ans
