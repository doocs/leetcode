class Solution:
    def maxPartitionsAfterOperations(self, s: str, k: int) -> int:
        @cache
        def dfs(i: int, cur: int, t: int) -> int:
            if i >= n:
                return 1
            v = 1 << (ord(s[i]) - ord("a"))
            nxt = cur | v
            if nxt.bit_count() > k:
                ans = dfs(i + 1, v, t) + 1
            else:
                ans = dfs(i + 1, nxt, t)
            if t:
                for j in range(26):
                    nxt = cur | (1 << j)
                    if nxt.bit_count() > k:
                        ans = max(ans, dfs(i + 1, 1 << j, 0) + 1)
                    else:
                        ans = max(ans, dfs(i + 1, nxt, 0))
            return ans

        n = len(s)
        return dfs(0, 0, 1)
