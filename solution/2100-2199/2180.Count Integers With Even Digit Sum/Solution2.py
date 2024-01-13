class Solution:
    def countEven(self, num: int) -> int:
        ans = num // 10 * 5 - 1
        x, s = num // 10, 0
        while x:
            s += x % 10
            x //= 10
        ans += (num % 10 + 2 - (s & 1)) >> 1
        return ans
