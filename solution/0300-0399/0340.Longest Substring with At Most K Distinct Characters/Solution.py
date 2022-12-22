class Solution:
    def lengthOfLongestSubstringKDistinct(self, s: str, k: int) -> int:
        cnt = Counter()
        n = len(s)
        ans = j = 0
        for i, c in enumerate(s):
            cnt[c] += 1
            while len(cnt) > k:
                cnt[s[j]] -= 1
                if cnt[s[j]] == 0:
                    cnt.pop(s[j])
                j += 1
            ans = max(ans, i - j + 1)
        return ans
