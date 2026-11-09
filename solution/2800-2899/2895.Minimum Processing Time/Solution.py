class Solution:
    def minProcessingTime(self, processorTime: list[int], tasks: list[int]) -> int:
        processorTime.sort()
        tasks.sort()
        ans = 0
        i = len(tasks) - 1
        for t in processorTime:
            ans = max(ans, t + tasks[i])
            i -= 4
        return ans
