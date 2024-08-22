class Solution:
    def sumOfPower(self, nums: List[int], k: int) -> int:
        mod = 10**9 + 7
        f = [1] + [0] * k
        for x in nums:
            for j in range(k, -1, -1):
                f[j] = (f[j] * 2 + (0 if j < x else f[j - x])) % mod
        return f[k]
