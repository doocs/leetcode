class Solution:
    def countKeyChanges(self, s: str) -> int:
        return sum(a.lower() != b.lower() for a, b in pairwise(s))
