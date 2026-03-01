class Solution:
    def trimTrailingVowels(self, s: str) -> str:
        i = len(s) - 1
        while i >= 0 and s[i] in "aeiou":
            i -= 1
        return s[: i + 1]
