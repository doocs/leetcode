class Solution:
    def reachableNodes(
        self, n: int, edges: List[List[int]], restricted: List[int]
    ) -> int:
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = [False] * n
        for i in restricted:
            vis[i] = True
        ans = 0
        stk = [0]
        while stk:
            i = stk.pop()
            if vis[i]:
                continue
            vis[i] = True
            ans += 1
            for j in g[i]:
                if not vis[j]:
                    stk.append(j)
        return ans
