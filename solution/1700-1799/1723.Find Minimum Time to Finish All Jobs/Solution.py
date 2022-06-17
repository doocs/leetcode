class Solution:
    def minimumTimeRequired(self, jobs: List[int], k: int) -> int:
        def dfs(i):
            nonlocal ans
            if i == len(jobs):
                ans = min(ans, max(cnt))
                return
            for j in range(k):
                if cnt[j] + jobs[i] >= ans:
                    continue
                cnt[j] += jobs[i]
                dfs(i + 1)
                cnt[j] -= jobs[i]
                if cnt[j] == 0:
                    break

        cnt = [0] * k
        jobs.sort(reverse=True)
        ans = inf
        dfs(0)
        return ans
