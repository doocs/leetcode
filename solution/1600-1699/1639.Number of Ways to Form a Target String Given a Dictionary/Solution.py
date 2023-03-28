class Solution:
    def numWays(self, words: List[str], target: str) -> int:
        @cache
        def dfs(i, j):
            if i >= m:
                return 1
            if j >= n:
                return 0
            ans = dfs(i, j + 1) + dfs(i + 1, j + 1) * cnt[j][ord(target[i]) - ord("a")]
            ans %= mod
            return ans

        m = len(target)
        n = len(words[0])
        cnt = [[0] * 26 for _ in range(n)]
        for w in words:
            for j, c in enumerate(w):
                cnt[j][ord(c) - ord("a")] += 1
        mod = 10**9 + 7
        return dfs(0, 0)
