class Solution:
    def findMinFibonacciNumbers(self, k: int) -> int:
        def dfs(k):
            if k < 2:
                return k
            a = b = 1
            while b <= k:
                a, b = b, a + b
            return 1 + dfs(k - a)

        return dfs(k)
