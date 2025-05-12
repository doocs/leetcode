class Solution:
    def minDeletion(self, s: str, k: int) -> int:
        return sum(sorted(Counter(s).values())[:-k])
