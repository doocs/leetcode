class Solution:
    def maxProfitAssignment(
        self, difficulty: List[int], profit: List[int], worker: List[int]
    ) -> int:
        n = len(difficulty)
        job = [(difficulty[i], profit[i]) for i in range(n)]
        job.sort(key=lambda x: x[0])
        worker.sort()
        i = t = res = 0
        for w in worker:
            while i < n and job[i][0] <= w:
                t = max(t, job[i][1])
                i += 1
            res += t
        return res
