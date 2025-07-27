class Solution:
    def numOfSubsequences(self, s: str) -> int:
        def calc(t: str) -> int:
            cnt = a = 0
            for c in s:
                if c == t[1]:
                    cnt += a
                a += int(c == t[0])
            return cnt

        l, r = 0, s.count("T")
        ans = mx = 0
        for c in s:
            r -= int(c == "T")
            if c == "C":
                ans += l * r
            l += int(c == "L")
            mx = max(mx, l * r)
        mx = max(mx, calc("LC"), calc("CT"))
        ans += mx
        return ans
