class Solution:
    def minTime(self, time: List[int], m: int) -> int:
        def check(t):
            s = mx = 0
            d = 1
            for x in time:
                s += x
                mx = max(mx, x)
                if s - mx > t:
                    d += 1
                    s = mx = x
            return d <= m

        return bisect_left(range(sum(time)), True, key=check)
