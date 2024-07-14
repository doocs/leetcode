class Solution:
    def numberOfSpecialChars(self, word: str) -> int:
        s = set(word)
        return sum(a in s and b in s for a, b in zip(ascii_lowercase, ascii_uppercase))
