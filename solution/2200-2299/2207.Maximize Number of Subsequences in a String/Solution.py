class Solution:
    def maximumSubsequenceCount(self, text: str, pattern: str) -> int:
        ans = 0
        cnt = Counter()
        for c in text:
            if c == pattern[1]:
                ans += cnt[pattern[0]]
            cnt[c] += 1
        ans += max(cnt[pattern[0]], cnt[pattern[1]])
        return ans
