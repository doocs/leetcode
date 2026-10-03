class Solution:
    def validPartition(self, nums: List[int]) -> bool:
        n = len(nums)
        f = [False] * n + [True]
        for i in range(n - 1, -1, -1):
            a = i + 1 < n and nums[i] == nums[i + 1]
            b = i + 2 < n and nums[i] == nums[i + 1] == nums[i + 2]
            c = (
                i + 2 < n
                and nums[i + 1] - nums[i] == 1
                and nums[i + 2] - nums[i + 1] == 1
            )
            f[i] = (a and f[i + 2]) or ((b or c) and f[i + 3])
        return f[0]
