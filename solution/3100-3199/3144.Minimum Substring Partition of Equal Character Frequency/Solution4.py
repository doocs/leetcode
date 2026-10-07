class Solution:
    def minimumSubstringsInPartition(self, s: str) -> int:
        n = len(s)
        f = [0] * (n + 1)
        for i in range(n - 1, -1, -1):
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
                if len(freq) == 1:
                    ans = min(ans, 1 + f[j + 1])
            f[i] = ans
        return f[0]
