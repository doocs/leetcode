class Solution:
    def minimumFlips(
        self, n: int, edges: List[List[int]], start: str, target: str
    ) -> List[int]:
        g = [[] for _ in range(n)]
        for i, (a, b) in enumerate(edges):
            g[a].append((b, i))
            g[b].append((a, i))
        ans = []
        need = [False] * n
        stk = [(0, -1, 0)]
        while stk:
            a, fa, state = stk.pop()
            if state == 0:
                stk.append((a, fa, 1))
                for b, _ in g[a]:
                    if b != fa:
                        stk.append((b, a, 0))
            else:
                rev = start[a] != target[a]
                for b, i in g[a]:
                    if b != fa and need[b]:
                        ans.append(i)
                        rev = not rev
                need[a] = rev
        if need[0]:
            return [-1]
        ans.sort()
        return ans
