class Solution:
    def countDigitOccurrences(self, nums: list[int], digit: int) -> int:
        ans = 0
        for x in nums:
            while x:
                v = x % 10
                if v == digit:
                    ans += 1
                x //= 10
        return ans
