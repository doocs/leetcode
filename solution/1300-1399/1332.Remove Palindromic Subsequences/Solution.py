class Solution:
    def removePalindromeSub(self, s: str) -> int:
        if not s:
            return 0
        if s[::-1] == s:
            return 1
        return 2
