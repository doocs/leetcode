class Solution:
    def findMinimumTime(self, tasks: List[List[int]]) -> int:
        tasks.sort(key=lambda x: x[1])
        vis = [0] * 2010
        ans = 0
        for start, end, duration in tasks:
            duration -= sum(vis[start : end + 1])
            i = end
            while i >= start and duration > 0:
                if not vis[i]:
                    duration -= 1
                    vis[i] = 1
                    ans += 1
                i -= 1
        return ans
