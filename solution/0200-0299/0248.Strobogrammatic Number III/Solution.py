class Solution:
    def strobogrammaticInRange(self, low: str, high: str) -> int:
        def dfs(u):
            if u == 0:
                return ['']
            if u == 1:
                return ['0', '1', '8']
            ans = []
            for v in dfs(u - 2):
                for l, r in ('11', '88', '69', '96'):
                    ans.append(l + v + r)
                if u != n:
                    ans.append('0' + v + '0')
            return ans

        a, b = len(low), len(high)
        low, high = int(low), int(high)
        ans = 0
        for n in range(a, b + 1):
            for s in dfs(n):
                if low <= int(s) <= high:
                    ans += 1
        return ans
