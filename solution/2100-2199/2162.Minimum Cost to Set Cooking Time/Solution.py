class Solution:
    def minCostSetTime(
        self, startAt: int, moveCost: int, pushCost: int, targetSeconds: int
    ) -> int:
        def f(m, s):
            if not 0 <= m < 100 or not 0 <= s < 100:
                return inf
            arr = [m // 10, m % 10, s // 10, s % 10]
            i = 0
            while i < 4 and arr[i] == 0:
                i += 1
            t = 0
            prev = startAt
            for v in arr[i:]:
                if v != prev:
                    t += moveCost
                t += pushCost
                prev = v
            return t

        m, s = divmod(targetSeconds, 60)
        ans = min(f(m, s), f(m - 1, s + 60))
        return ans
