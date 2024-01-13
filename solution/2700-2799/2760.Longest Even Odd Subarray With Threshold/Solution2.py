class Solution:
    def longestAlternatingSubarray(self, nums: List[int], threshold: int) -> int:
        ans, l, n = 0, 0, len(nums)
        while l < n:
            if nums[l] % 2 == 0 and nums[l] <= threshold:
                r = l + 1
                while r < n and nums[r] % 2 != nums[r - 1] % 2 and nums[r] <= threshold:
                    r += 1
                ans = max(ans, r - l)
                l = r
            else:
                l += 1
        return ans
