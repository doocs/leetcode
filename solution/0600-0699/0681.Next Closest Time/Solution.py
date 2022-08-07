class Solution:
    def nextClosestTime(self, time: str) -> str:
        def check(t):
            h, m = int(t[:2]), int(t[2:])
            return 0 <= h < 24 and 0 <= m < 60

        def dfs(curr):
            if len(curr) == 4:
                if not check(curr):
                    return
                nonlocal ans, d
                p = int(curr[:2]) * 60 + int(curr[2:])
                if t < p < t + d:
                    d = p - t
                    ans = curr[:2] + ':' + curr[2:]
                return
            for c in s:
                dfs(curr + c)

        s = {c for c in time if c != ':'}
        t = int(time[:2]) * 60 + int(time[3:])
        d = inf
        ans = None
        dfs('')
        if ans is None:
            mi = min(int(c) for c in s)
            ans = f'{mi}{mi}:{mi}{mi}'
        return ans
