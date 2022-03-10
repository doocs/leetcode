class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        n = len(nums)
        prefix = [0] * n
        suffix = [0] * n
        res = 0
        for i in range(n):
            if i == 0:
                prefix[i] = nums[i]
            else:
                prefix[i] = 0 if nums[i] == 0 else prefix[i - 1] + 1
            res = max(res, prefix[i])

        for i in range(n - 1, -1, -1):
            if i == n - 1:
                suffix[i] = nums[i]
            else:
                suffix[i] = 0 if nums[i] == 0 else suffix[i + 1] + 1

        for i in range(n):
            if nums[i] == 0:
                t = 1
                if i > 0:
                    t += prefix[i - 1]
                if i < n - 1:
                    t += suffix[i + 1]
                res = max(res, t)
        return res
