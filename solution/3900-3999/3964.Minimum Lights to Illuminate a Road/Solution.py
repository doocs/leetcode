class Solution:
    def minLights(self, lights: list[int]) -> int:
        n = len(lights)
        d = [0] * n
        for i, v in enumerate(lights):
            if v > 0:
                l = max(0, i - v)
                r = min(n - 1, i + v)
                d[l] += 1
                if r + 1 < n:
                    d[r + 1] -= 1
        s = cnt = 0
        ans = 0
        for x in d:
            s += x
            if s == 0:
                cnt += 1
            else:
                ans += (cnt + 2) // 3
                cnt = 0
        ans += (cnt + 2) // 3
        return ans
