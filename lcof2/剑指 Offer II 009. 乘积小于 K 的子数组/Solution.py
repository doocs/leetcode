class Solution:
    def numSubarrayProductLessThanK(self, nums: List[int], k: int) -> int:
        n = len(nums)
        ans = 0
        sum = 1
        left, right = 0, 0
        while right < n:
            sum *= nums[right]
            right += 1
            while sum >= k and left < right:
                sum /= nums[left]
                left += 1
            ans += right - left
        return ans
