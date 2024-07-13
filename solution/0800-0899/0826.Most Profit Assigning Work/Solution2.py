class Solution:
    def maxProfitAssignment(
        self, difficulty: List[int], profit: List[int], worker: List[int]
    ) -> int:
        m = max(difficulty)
        f = [0] * (m + 1)
        for d, p in zip(difficulty, profit):
            f[d] = max(f[d], p)
        for i in range(1, m + 1):
            f[i] = max(f[i], f[i - 1])
        return sum(f[min(w, m)] for w in worker)
