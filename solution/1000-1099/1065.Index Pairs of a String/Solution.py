class Solution:
    def indexPairs(self, text: str, words: list[str]) -> list[list[int]]:
        words = set(words)
        n = len(text)
        return [
            [i, j] for i in range(n) for j in range(i, n) if text[i : j + 1] in words
        ]
