class Solution:
    def minimumTime(self, n: int, relations: List[List[int]], time: List[int]) -> int:
        g = defaultdict(list)
        indeg = [0] * n
        for a, b in relations:
            g[a - 1].append(b - 1)
            indeg[b - 1] += 1
        q = deque()
        dp = [0] * n
        ans = 0
        for i, (v, t) in enumerate(zip(indeg, time)):
            if v == 0:
                q.append(i)
                dp[i] = t
                ans = max(ans, t)
        while q:
            i = q.popleft()
            for j in g[i]:
                dp[j] = max(dp[j], dp[i] + time[j])
                ans = max(ans, dp[j])
                indeg[j] -= 1
                if indeg[j] == 0:
                    q.append(j)
        return ans
