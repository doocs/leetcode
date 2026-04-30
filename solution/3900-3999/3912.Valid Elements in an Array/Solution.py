class Solution:
    def findValidElements(self, nums: list[int]) -> list[int]:
        n = len(nums)
        right = [nums[-1]] * n
        for i in range(n - 2, -1, -1):
            right[i] = max(right[i + 1], nums[i])
        left = 0
        ans = []
        for i, x in enumerate(nums):
            if x > left or i == n - 1 or x > right[i + 1]:
                ans.append(x)
            left = max(left, x)
        return ans
