class Solution:
    def maximumScoreAfterOperations(
        self, edges: List[List[int]], values: List[int]
    ) -> int:
        n = len(values)
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        sub = [(0, 0)] * n
        stk = [(0, -1, 0)]
        while stk:
            i, fa, state = stk.pop()
            if state == 0:
                stk.append((i, fa, 1))
                for j in g[i]:
                    if j != fa:
                        stk.append((j, i, 0))
            else:
                a = b = 0
                leaf = True
                for j in g[i]:
                    if j != fa:
                        leaf = False
                        aa, bb = sub[j]
                        a += aa
                        b += bb
                if leaf:
                    sub[i] = (values[i], 0)
                else:
                    sub[i] = (values[i] + a, max(values[i] + b, a))
        return sub[0][1]
