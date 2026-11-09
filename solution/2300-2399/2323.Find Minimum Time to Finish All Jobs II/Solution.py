class Solution:
    def minimumTime(self, jobs: list[int], workers: list[int]) -> int:
        jobs.sort()
        workers.sort()
        return max((a + b - 1) // b for a, b in zip(jobs, workers))
