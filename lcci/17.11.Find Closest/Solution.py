class Solution:
    def findClosest(self, words: List[str], word1: str, word2: str) -> int:
        i, j, ans = 1e5, -1e5, 1e5
        for k, word in enumerate(words):
            if word == word1:
                i = k
            elif word == word2:
                j = k
            ans = min(ans, abs(i - j))
        return ans
