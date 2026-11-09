class Solution:
    def hardestWorker(self, n: int, logs: list[list[int]]) -> int:
        last = mx = ans = 0
        for uid, t in logs:
            t -= last
            if mx < t or (mx == t and ans > uid):
                ans, mx = uid, t
            last += t
        return ans
