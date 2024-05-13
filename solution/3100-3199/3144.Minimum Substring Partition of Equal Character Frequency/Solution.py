class Solution:
    def minimumSubstringsInPartition(self, s: str) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= n:
                return 0
            cnt = defaultdict(int)
            freq = defaultdict(int)
            ans = n - i
            for j in range(i, n):
                if cnt[s[j]]:
                    freq[cnt[s[j]]] -= 1
                    if not freq[cnt[s[j]]]:
                        freq.pop(cnt[s[j]])
                cnt[s[j]] += 1
                freq[cnt[s[j]]] += 1
                if len(freq) == 1 and (t := 1 + dfs(j + 1)) < ans:
                    ans = t
            return ans

        n = len(s)
        return dfs(0)
