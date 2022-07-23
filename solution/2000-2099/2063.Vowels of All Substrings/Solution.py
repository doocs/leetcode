class Solution:
    def countVowels(self, word: str) -> int:
        n = len(word)
        return sum((i + 1) * (n - i) for i, c in enumerate(word) if c in 'aeiou')
