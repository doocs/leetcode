class Solution:
    def maxFrequency(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans = 1
        window = 0
        l, r, n = 0, 1, len(nums)
        while r < n:
            window += (nums[r] - nums[r - 1]) * (r - l)
            r += 1
            while window > k:
                window -= nums[r - 1] - nums[l]
                l += 1
            ans = max(ans, r - l)
        return ans
