class Solution:
    def longestDecomposition(self, text: str) -> int:
        n = len(text)
        if n < 2:
            return n
        for i in range(n // 2 + 1):
            if text[:i] == text[-i:]:
                return 2 + self.longestDecomposition(text[i:-i])
        return 1
