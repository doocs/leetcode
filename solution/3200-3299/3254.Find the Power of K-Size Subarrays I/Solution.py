class Solution:
    def resultsArray(self, nums: list[int], k: int) -> list[int]:
        n = len(nums)
        f = [1] * n
        for i in range(1, n):
            if nums[i] == nums[i - 1] + 1:
                f[i] = f[i - 1] + 1
        return [nums[i] if f[i] >= k else -1 for i in range(k - 1, n)]
