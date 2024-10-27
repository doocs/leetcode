class Solution:
    def possibleStringCount(self, word: str) -> int:
        return 1 + sum(x == y for x, y in pairwise(word))
