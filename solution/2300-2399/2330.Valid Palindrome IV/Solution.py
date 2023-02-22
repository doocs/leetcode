class Solution:
    def makePalindrome(self, s: str) -> bool:
        i, j = 0, len(s) - 1
        cnt = 0
        while i < j:
            cnt += s[i] != s[j]
            i, j = i + 1, j - 1
        return cnt <= 2
