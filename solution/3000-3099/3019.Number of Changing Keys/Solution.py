class Solution:
    def countKeyChanges(self, s: str) -> int:
        return sum(s.lower()[i] != s.lower()[i - 1] for i in range(1, len(s)))
