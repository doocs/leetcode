class Solution:
    def minimumOperationsToMakeKPeriodic(self, word: str, k: int) -> int:
        n = len(word)
        return n // k - max(Counter(word[i : i + k] for i in range(0, n, k)).values())
