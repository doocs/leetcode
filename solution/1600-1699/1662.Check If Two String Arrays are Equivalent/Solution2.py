class Solution:
    def arrayStringsAreEqual(self, word1: list[str], word2: list[str]) -> bool:
        i1 = j1 = i2 = j2 = 0
        while i1 < len(word1) and i2 < len(word2):
            if word1[i1][j1] != word2[i2][j2]:
                return False
            j1 += 1
            if j1 == len(word1[i1]):
                i1, j1 = i1 + 1, 0
            j2 += 1
            if j2 == len(word2[i2]):
                i2, j2 = i2 + 1, 0
        return i1 == len(word1) and i2 == len(word2)
