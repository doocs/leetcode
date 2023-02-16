class Solution:
    def numSubseq(self, nums: List[int], target: int) -> int:
        mod = 10**9 + 7
        nums.sort()
        n = len(nums)
        f = [1] + [0] * n
        for i in range(1, n + 1):
            f[i] = f[i - 1] * 2 % mod
        ans = 0
        for i, x in enumerate(nums):
            if x * 2 > target:
                break
            j = bisect_right(nums, target - x, i + 1) - 1
            ans = (ans + f[j - i]) % mod
        return ans
