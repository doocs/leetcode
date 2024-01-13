class Solution:
    def countLetters(self, s: str) -> int:
        ans = 0
        i, n = 0, len(s)
        while i < n:
            j = i
            cnt = 0
            while j < n and s[j] == s[i]:
                j += 1
                cnt += 1
                ans += cnt
            i = j
        return ans
