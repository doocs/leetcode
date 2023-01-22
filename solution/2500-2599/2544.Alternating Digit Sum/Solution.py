class Solution:
    def alternateDigitSum(self, n: int) -> int:
        ans, sign = 0, 1
        for c in str(n):
            x = int(c)
            ans += sign * x
            sign *= -1
        return ans
