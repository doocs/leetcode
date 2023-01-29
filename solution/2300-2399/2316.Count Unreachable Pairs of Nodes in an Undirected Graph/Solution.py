class Solution:
    def countPairs(self, n: int, edges: List[List[int]]) -> int:
        def dfs(i):
            vis.add(i)
            cnt = 1
            for j in g[i]:
                if j not in vis:
                    cnt += dfs(j)
            return cnt

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = set()
        ans = s = 0
        for i in range(n):
            if i not in vis:
                t = dfs(i)
                ans += s * t
                s += t
        return ans
