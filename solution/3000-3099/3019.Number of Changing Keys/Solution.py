class Solution:
    def countKeyChanges(self, s: str) -> int:
        return sum(a != b for a, b in pairwise(s.lower()))
