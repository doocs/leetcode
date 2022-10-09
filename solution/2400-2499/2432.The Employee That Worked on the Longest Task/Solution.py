class Solution:
    def hardestWorker(self, n: int, logs: List[List[int]]) -> int:
        ans = mx = last = 0
        for uid, t in logs:
            x = t - last
            if mx < x:
                mx = x
                ans = uid
            elif mx == x and ans > uid:
                ans = uid
            last = t
        return ans
