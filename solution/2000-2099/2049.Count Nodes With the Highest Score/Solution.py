class Solution:
    def countHighestScoreNodes(self, parents: List[int]) -> int:
        def dfs(i: int, fa: int):
            cnt = score = 1
            for j in g[i]:
                if j != fa:
                    t = dfs(j, i)
                    score *= t
                    cnt += t
            if n - cnt:
                score *= n - cnt
            nonlocal ans, mx
            if mx < score:
                mx = score
                ans = 1
            elif mx == score:
                ans += 1
            return cnt

        n = len(parents)
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parents[i]].append(i)
        ans = mx = 0
        dfs(0, -1)
        return ans
