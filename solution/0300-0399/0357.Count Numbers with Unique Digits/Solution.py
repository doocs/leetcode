class Solution:
    def countNumbersWithUniqueDigits(self, n: int) -> int:
        if n == 0:
            return 1
        if n == 1:
            return 10
        ans, cur = 10, 9
        for i in range(n - 1):
            cur *= 9 - i
            ans += cur
        return ans
