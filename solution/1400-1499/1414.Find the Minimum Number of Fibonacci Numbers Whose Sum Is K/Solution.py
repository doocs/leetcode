class Solution:
    def findMinFibonacciNumbers(self, k: int) -> int:
        a = b = 1
        while b <= k:
            a, b = b, a + b
        ans = 0
        while k:
            if k >= b:
                k -= b
                ans += 1
            a, b = b - a, a
        return ans
