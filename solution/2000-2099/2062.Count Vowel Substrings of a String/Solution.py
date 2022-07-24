class Solution:
    def countVowelSubstrings(self, word: str) -> int:
        n = len(word)
        s = set('aeiou')
        return sum(set(word[i:j]) == s for i in range(n) for j in range(i + 1, n + 1))
