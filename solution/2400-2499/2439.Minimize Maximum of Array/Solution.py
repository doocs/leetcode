class Solution:
    def minimizeArrayValue(self, nums: List[int]) -> int:
        def check(mx):
            d = 0
            for x in nums[:0:-1]:
                d = max(0, d + x - mx)
            return nums[0] + d <= mx

        left, right = 0, max(nums)
        while left < right:
            mid = (left + right) >> 1
            if check(mid):
                right = mid
            else:
                left = mid + 1
        return left
