class Solution:
    def countHighestScoreNodes(self, parents: List[int]) -> int:
        n, max_score, ans = len(parents), 0, 0
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parents[i]].append(i)

        def dfs(cur: int) -> int:
            nonlocal max_score, ans
            size, score = 1, 1
            for c in g[cur]:
                s = dfs(c)
                size += s
                score *= s
            if cur > 0:
                score *= n - size
            if score > max_score:
                max_score = score
                ans = 1
            elif score == max_score:
                ans += 1
            return size

        dfs(0)
        return ans
