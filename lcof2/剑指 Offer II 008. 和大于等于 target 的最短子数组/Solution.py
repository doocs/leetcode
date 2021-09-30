class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        n = len(nums)
        ans = float('inf')
        sum = 0
        left, right = 0, 0
        while right < n:
            sum += nums[right]
            right += 1
            while sum >= target:
                ans = min(ans, right - left)
                sum -= nums[left]
                left += 1
        return 0 if ans == float('inf') else ans
