class Solution:
    def findPermutationDifference(self, s: str, t: str) -> int:
        d = {c: i for i, c in enumerate(s)}
        return sum(abs(d[c] - i) for i, c in enumerate(t))
