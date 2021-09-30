class Solution:
    def arrayStringsAreEqual(self, word1: List[str], word2: List[str]) -> bool:
        s1, s2 = ''.join(word1), ''.join(word2)
        return s1 == s2
