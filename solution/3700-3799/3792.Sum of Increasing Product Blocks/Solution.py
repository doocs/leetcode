class Solution:
    def sumOfBlocks(self, n: int) -> int:
        ans = 0
        mod = 10**9 + 7
        k = 1
        for i in range(1, n + 1):
            x = 1
            for j in range(k, k + i):
                x = (x * j) % mod
            ans = (ans + x) % mod
            k += i
        return ans
