class Solution:
    def calculateTime(self, keyboard: str, word: str) -> int:
        index = {c: i for i, c in enumerate(keyboard)}
        res = t = 0
        for c in word:
            res += abs(index[c] - t)
            t = index[c]
        return res
