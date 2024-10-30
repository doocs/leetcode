class Solution:
    def numberOfWays(self, s: str) -> int:
        l = [0, 0]
        r = [s.count("0"), s.count("1")]
        ans = 0
        for x in map(int, s):
            r[x] -= 1
            ans += l[x ^ 1] * r[x ^ 1]
            l[x] += 1
        return ans
