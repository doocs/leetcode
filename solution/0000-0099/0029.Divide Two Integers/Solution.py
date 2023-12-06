class Solution:
    def divide(self, a: int, b: int) -> int:
        if b == 1:
            return a
        if a == -(2**31) and b == -1:
            return 2**31 - 1
        sign = (a > 0 and b > 0) or (a < 0 and b < 0)
        a = -a if a > 0 else a
        b = -b if b > 0 else b
        ans = 0
        while a <= b:
            x = b
            cnt = 1
            while x >= (-(2**30)) and a <= (x << 1):
                x <<= 1
                cnt <<= 1
            a -= x
            ans += cnt
        return ans if sign else -ans
