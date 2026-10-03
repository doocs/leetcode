class Solution:
    def minimumSubstringsInPartition(self, s: str) -> int:
        n = len(s)
        f = [0] * (n + 1)
        for i in range(n - 1, -1, -1):
            cnt = defaultdict(int)
            m = 0
            ans = n - i
            for j in range(i, n):
                cnt[s[j]] += 1
                m = max(m, cnt[s[j]])
                if j - i + 1 == m * len(cnt):
                    ans = min(ans, 1 + f[j + 1])
            f[i] = ans
        return f[0]
