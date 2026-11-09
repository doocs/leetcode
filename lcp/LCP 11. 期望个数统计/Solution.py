class Solution:
    def expectNumber(self, scores: list[int]) -> int:
        return len(set(scores))
