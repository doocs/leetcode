class Solution:
    def minimumEffort(self, tasks: List[List[int]]) -> int:
        ans = t = 0
        for a, m in sorted(tasks, key=lambda x: x[0] - x[1]):
            if t < m:
                ans += m - t
                t = m
            t -= a
        return ans
