class Solution:
    def halvesAreAlike(self, s: str) -> bool:
        half = len(s) >> 1
        vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'}
        s1 = sum(1 for c in s[:half] if c in vowels)
        s2 = sum(1 for c in s[half:] if c in vowels)
        return s1 == s2
