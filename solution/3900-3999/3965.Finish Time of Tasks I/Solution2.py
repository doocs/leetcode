class Solution:
    def finishTime(self, n: int, edges: List[List[int]], baseTime: List[int]) -> int:
        g = [[] for _ in range(n)]
        for u, v in edges:
            g[u].append(v)
        fin = [0] * n
        stk = [(0, 0)]
        while stk:
            i, state = stk.pop()
            if state == 0:
                if not g[i]:
                    fin[i] = baseTime[i]
                else:
                    stk.append((i, 1))
                    for j in g[i]:
                        stk.append((j, 0))
            else:
                earliest, latest = inf, -inf
                for j in g[i]:
                    a = fin[j]
                    earliest = min(earliest, a)
                    latest = max(latest, a)
                own = (latest - earliest) + baseTime[i]
                fin[i] = latest + own
        return fin[0]
