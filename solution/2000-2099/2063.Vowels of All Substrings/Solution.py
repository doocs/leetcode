class Solution:
    def countVowels(self, word: str) -> int:
        ans, n = 0, len(word)
        for i, c in enumerate(word):
            if c in ['a', 'e', 'i', 'o', 'u']:
                ans += (i + 1) * (n - i)
        return ans
