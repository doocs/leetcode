class Solution:
    def removePalindromeSub(self, s: str) -> int:
        return 1 if s[::-1] == s else 2
