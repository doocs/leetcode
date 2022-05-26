class Solution:
    def maximumDetonation(self, bombs: List[List[int]]) -> int:
        def check(i, j):
            if i == j:
                return False
            x, y = bombs[i][0] - bombs[j][0], bombs[i][1] - bombs[j][1]
            r = bombs[i][2]
            return r * r >= x * x + y * y

        g = defaultdict(list)
        n = len(bombs)
        for i in range(n):
            for j in range(n):
                if check(i, j):
                    g[i].append(j)
        ans = 0
        for k in range(n):
            q = deque([k])
            vis = [False] * n
            vis[k] = True
            cnt = 0
            while q:
                i = q.popleft()
                cnt += 1
                for j in g[i]:
                    if not vis[j]:
                        vis[j] = True
                        q.append(j)
            ans = max(ans, cnt)
        return ans
