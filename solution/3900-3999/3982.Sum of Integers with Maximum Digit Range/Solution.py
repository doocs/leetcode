class Solution:
    def maxDigitRange(self, nums: list[int]) -> int:
        ans = mx = 0
        for x in nums:
            a, b = 10, 0
            y = x
            while y:
                v = y % 10
                y //= 10
                a = min(a, v)
                b = max(b, v)
            r = b - a
            if mx < r:
                mx = r
                ans = x
            elif mx == r:
                ans += x
        return ans
