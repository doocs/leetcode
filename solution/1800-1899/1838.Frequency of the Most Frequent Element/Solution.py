class Solution:
    def maxFrequency(self, nums: List[int], k: int) -> int:
        def check(m: int) -> bool:
            for i in range(m, n + 1):
                if nums[i - 1] * m - (s[i] - s[i - m]) <= k:
                    return True
            return False

        n = len(nums)
        nums.sort()
        s = list(accumulate(nums, initial=0))
        l, r = 1, n
        while l < r:
            mid = (l + r + 1) >> 1
            if check(mid):
                l = mid
            else:
                r = mid - 1
        return l
