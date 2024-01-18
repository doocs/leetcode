class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        ans = nums.count(1)
        n = len(nums)
        left = [0] * n
        right = [0] * n
        for i, v in enumerate(nums):
            if v:
                left[i] = 1 if i == 0 else left[i - 1] + 1
        for i in range(n - 1, -1, -1):
            v = nums[i]
            if v:
                right[i] = 1 if i == n - 1 else right[i + 1] + 1
        ans = 0
        for i, v in enumerate(nums):
            t = 0
            if i:
                t += left[i - 1]
            if i < n - 1:
                t += right[i + 1]
            ans = max(ans, t + 1)
        return ans
