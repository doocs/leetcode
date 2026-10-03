class Solution:
    def countPairs(self, n: int, edges: List[List[int]]) -> int:
        def dfs(i: int) -> int:
            if vis[i]:
                return 0
            vis[i] = True
            stk = [i]
            cnt = 0
            while stk:
                u = stk.pop()
                cnt += 1
                for j in g[u]:
                    if not vis[j]:
                        vis[j] = True
                        stk.append(j)
            return cnt

        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = [False] * n
        ans = s = 0
        for i in range(n):
            t = dfs(i)
            ans += s * t
            s += t
        return ans
