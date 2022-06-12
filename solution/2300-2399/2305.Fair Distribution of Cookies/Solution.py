class Solution:
    def distributeCookies(self, cookies: List[int], k: int) -> int:
        def dfs(u):
            nonlocal ans
            if u == len(cookies):
                ans = min(ans, max(cnt))
                return
            for i in range(k):
                if cnt[i] + cookies[u] < ans:
                    cnt[i] += cookies[u]
                    dfs(u + 1)
                    cnt[i] -= cookies[u]

        ans = inf
        cnt = [0] * k
        dfs(0)
        return ans
