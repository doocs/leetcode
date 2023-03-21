class Solution:
    def taskSchedulerII(self, tasks: List[int], space: int) -> int:
        day = defaultdict(int)
        ans = 0
        for task in tasks:
            ans += 1
            ans = max(ans, day[task])
            day[task] = ans + space + 1
        return ans
