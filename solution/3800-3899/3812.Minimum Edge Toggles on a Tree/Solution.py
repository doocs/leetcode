class Solution:
    def minimumFlips(
        self, n: int, edges: List[List[int]], start: str, target: str
    ) -> List[int]:
        g = [[] for _ in range(n)]
        for i, (a, b) in enumerate(edges):
            g[a].append((b, i))
            g[b].append((a, i))

        ans = []

        def dfs(a: int, fa: int) -> bool:
            rev = start[a] != target[a]
            for b, i in g[a]:
                if b != fa and dfs(b, a):
                    ans.append(i)
                    rev = not rev
            return rev

        if dfs(0, -1):
            return [-1]
        ans.sort()
        return ans
