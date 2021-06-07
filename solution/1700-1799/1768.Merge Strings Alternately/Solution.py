class Solution:
    def mergeAlternately(self, word1: str, word2: str) -> str:
        i, m, n = 0, len(word1), len(word2)
        res = []
        while i < m or i < n:
            if i < m:
                res.append(word1[i])
            if i < n:
                res.append(word2[i])
            i += 1
        return ''.join(res)
