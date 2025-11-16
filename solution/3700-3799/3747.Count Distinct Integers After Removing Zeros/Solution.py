class Solution:
    def countDistinct(self, n: int) -> int:
        @cache
        def dfs(i: int, zero: bool, lead: bool, lim: bool) -> int:
            if i >= len(s):
                return 1 if (not zero and not lead) else 0
            up = int(s[i]) if lim else 9
            ans = 0
            for j in range(up + 1):
                nxt_zero = zero or (j == 0 and not lead)
                nxt_lead = lead and j == 0
                nxt_lim = lim and j == up
                ans += dfs(i + 1, nxt_zero, nxt_lead, nxt_lim)
            return ans

        s = str(n)
        return dfs(0, False, True, True)
