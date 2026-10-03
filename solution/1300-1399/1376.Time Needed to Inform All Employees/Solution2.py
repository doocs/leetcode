class Solution:
    def numOfMinutes(
        self, n: int, headID: int, manager: List[int], informTime: List[int]
    ) -> int:
        g = [[] for _ in range(n)]
        for i, x in enumerate(manager):
            if x != -1:
                g[x].append(i)
        time = [0] * n
        stk = [(headID, 0)]
        while stk:
            i, state = stk.pop()
            if state == 0:
                stk.append((i, 1))
                for j in g[i]:
                    stk.append((j, 0))
            else:
                ans = 0
                for j in g[i]:
                    ans = max(ans, time[j] + informTime[i])
                time[i] = ans
        return time[headID]
