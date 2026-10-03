class Solution:
    def countPairsOfConnectableServers(
        self, edges: List[List[int]], signalSpeed: int
    ) -> List[int]:
        n = len(edges) + 1
        g = [[] for _ in range(n)]
        for a, b, w in edges:
            g[a].append((b, w))
            g[b].append((a, w))

        def count(start: int, fa: int, dist: int) -> int:
            cnt = 0
            stk = [(start, fa, dist)]
            while stk:
                a, parent, ws = stk.pop()
                if ws % signalSpeed == 0:
                    cnt += 1
                for b, w in g[a]:
                    if b != parent:
                        stk.append((b, a, ws + w))
            return cnt

        ans = [0] * n
        for a in range(n):
            s = 0
            for b, w in g[a]:
                t = count(b, a, w)
                ans[a] += s * t
                s += t
        return ans
