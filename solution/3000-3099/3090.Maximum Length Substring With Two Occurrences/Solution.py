class Solution:
    def maximumLengthSubstring(self, s: str) -> int:
        ans = l = 0
        cnt = defaultdict(int)
        for r, c in enumerate(s):
            cnt[c] += 1
            while cnt[c] > 2:
                cnt[s[l]] -= 1
                l += 1
            ans = max(ans, r - l + 1)
        return ans
