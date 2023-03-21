class Solution:
    def firstPalindrome(self, words: List[str]) -> str:
        return next((w for w in words if w == w[::-1]), "")
