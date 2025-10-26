class Solution:
    def maxSumOfSquares(self, num: int, sum: int) -> str:
        if num * 9 < sum:
            return ""
        k, s = divmod(sum, 9)
        ans = "9" * k
        if s:
            ans += digits[s]
        ans += "0" * (num - len(ans))
        return ans
