class Solution:
    def expectNumber(self, scores: List[int]) -> int:
        return len(set(scores))
