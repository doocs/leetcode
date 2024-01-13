class Solution:
    def minSessions(self, tasks: List[int], sessionTime: int) -> int:
        n = len(tasks)
        ok = [False] * (1 << n)
        for i in range(1, 1 << n):
            t = sum(tasks[j] for j in range(n) if i >> j & 1)
            ok[i] = t <= sessionTime
        f = [inf] * (1 << n)
        f[0] = 0
        for i in range(1, 1 << n):
            j = i
            while j:
                if ok[j]:
                    f[i] = min(f[i], f[i ^ j] + 1)
                j = (j - 1) & i
        return f[-1]
