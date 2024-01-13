class Solution:
    def numSubarrayProductLessThanK(self, nums: List[int], k: int) -> int:
        s = 1
        ans = i = 0
        for j, x in enumerate(nums):
            s *= x
            while i <= j and s >= k:
                s //= nums[i]
                i += 1
            ans += j - i + 1
        return ans
