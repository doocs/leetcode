class Solution:
    def numberOfWays(self, s: str) -> int:
        n = len(s)
        cnt0 = s.count("0")
        cnt1 = n - cnt0
        c0 = c1 = 0
        ans = 0
        for c in s:
            if c == "0":
                ans += c1 * (cnt1 - c1)
                c0 += 1
            else:
                ans += c0 * (cnt0 - c0)
                c1 += 1
        return ans
