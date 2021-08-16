class Solution:
    def pivotIndex(self, nums: List[int]) -> int:
        n = len(nums)
        sum = [0 for _ in range(n + 1)]
        for i in range(1, n + 1):
            sum[i] = sum[i - 1] + nums[i - 1]
        for i in range(0, n):
            if sum[i] == sum[n] - sum[i + 1]:
                return i
        return -1
