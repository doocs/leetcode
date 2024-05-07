class Solution:
    def findClosest(self, words: List[str], word1: str, word2: str) -> int:
        i, j = inf, -inf
        ans = inf
        for k, w in enumerate(words):
            if w == word1:
                i = k
            elif w == word2:
                j = k
            ans = min(ans, abs(i - j))
        return ans
