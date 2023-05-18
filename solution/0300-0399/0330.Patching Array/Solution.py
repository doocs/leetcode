class Solution:
    def minPatches(self, nums: List[int], n: int) -> int:
        x = 1
        ans = i = 0
        while x <= n:
            if i < len(nums) and nums[i] <= x:
                x += nums[i]
                i += 1
            else:
                ans += 1
                x <<= 1
        return ans
