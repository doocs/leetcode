class Solution:
    def countGoodNodes(self, edges: List[List[int]]) -> int:
        def dfs(a: int, fa: int) -> int:
            pre = -1
            cnt = ok = 1
            for b in g[a]:
                if b != fa:
                    cur = dfs(b, a)
                    cnt += cur
                    if pre < 0:
                        pre = cur
                    elif pre != cur:
                        ok = 0
            nonlocal ans
            ans += ok
            return cnt

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        ans = 0
        dfs(0, -1)
        return ans
