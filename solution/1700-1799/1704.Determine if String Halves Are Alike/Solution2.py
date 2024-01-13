class Solution:
    def halvesAreAlike(self, s: str) -> bool:
        vowels = set('aeiouAEIOU')
        a, b = s[: len(s) >> 1], s[len(s) >> 1 :]
        return sum(c in vowels for c in a) == sum(c in vowels for c in b)
