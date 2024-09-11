class Solution:
    def numberOfWays(self, corridor: str) -> int:
        mod = 10**9 + 7
        ans, cnt, last = 1, 0, 0
        for i, c in enumerate(corridor):
            if c == "S":
                cnt += 1
                if cnt > 2 and cnt % 2:
                    ans = ans * (i - last) % mod
                last = i
        return ans if cnt and cnt % 2 == 0 else 0
