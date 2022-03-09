class Solution:
    def bestRotation(self, nums: List[int]) -> int:
        n = len(nums)
        mx, ans = -1, n
        d = [0] * n
        for i, v in enumerate(nums):
            l, r = (i + 1) % n, (n + i + 1 - v) % n
            d[l] += 1
            d[r] -= 1
        s = 0
        for k, t in enumerate(d):
            s += t
            if s > mx:
                mx = s
                ans = k
        return ans
