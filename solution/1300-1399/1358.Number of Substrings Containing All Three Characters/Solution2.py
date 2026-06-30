class Solution:
    def numberOfSubstrings(self, s: str) -> int:
        ans = l = 0
        cnt = Counter()
        for r, c in enumerate(s):
            cnt[c] += 1
            while cnt['a'] and cnt['b'] and cnt['c']:
                cnt[s[l]] -= 1
                l += 1
            ans += l
        return ans
