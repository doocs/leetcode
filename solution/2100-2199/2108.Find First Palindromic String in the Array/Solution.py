class Solution:
    def firstPalindrome(self, words: list[str]) -> str:
        return next((w for w in words if w == w[::-1]), "")
