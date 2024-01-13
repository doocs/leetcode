class Solution:
    def maxFrequency(self, nums: List[int], k: int) -> int:
        def check(cnt):
            for i in range(n + 1 - cnt):
                j = i + cnt - 1
                if nums[j] * cnt - (s[j + 1] - s[i]) <= k:
                    return True
            return False

        nums.sort()
        s = list(accumulate(nums, initial=0))
        n = len(nums)
        left, right = 1, n
        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
