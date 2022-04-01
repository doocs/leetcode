class Solution:
    def findClosest(self, words: List[str], word1: str, word2: str) -> int:
        idx1, idx2, ans = 10**5, -10**5, 10**5
        for i, word in enumerate(words):
            if word == word1:
                idx1 = i
            elif word == word2:
                idx2 = i
            ans = min(ans, abs(idx1 - idx2))
        return ans
