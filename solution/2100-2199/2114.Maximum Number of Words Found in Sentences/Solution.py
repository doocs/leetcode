class Solution:
    def mostWordsFound(self, sentences: list[str]) -> int:
        return 1 + max(s.count(' ') for s in sentences)
