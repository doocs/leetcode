class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        n = len(nums)
        pre_sum = [0] * (n + 1)
        for i in range(1, n + 1):
            pre_sum[i] = pre_sum[i - 1] + nums[i - 1]
        res = n + 1
        for i in range(1, n + 1):
            t = pre_sum[i - 1] + target
            left, right = 0, n
            while left < right:
                mid = (left + right) >> 1
                if pre_sum[mid] >= t:
                    right = mid
                else:
                    left = mid + 1
            if pre_sum[left] - pre_sum[i - 1] >= target:
                res = min(res, left - i + 1)
        return 0 if res == n + 1 else res
