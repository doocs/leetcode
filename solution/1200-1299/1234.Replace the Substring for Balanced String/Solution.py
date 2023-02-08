class Solution:
    def balancedString(self, s: str) -> int:
        cnt = Counter(s)
        n = len(s)
        if all(v <= n // 4 for v in cnt.values()):
            return 0
        ans, j = n, 0
        for i, c in enumerate(s):
            cnt[c] -= 1
            while j <= i and all(v <= n // 4 for v in cnt.values()):
                ans = min(ans, i - j + 1)
                cnt[s[j]] += 1
                j += 1
        return ans
