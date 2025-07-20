class Solution:
    def maxGCDScore(self, nums: List[int], k: int) -> int:
        n = len(nums)
        cnt = [0] * n
        for i, x in enumerate(nums):
            while x % 2 == 0:
                cnt[i] += 1
                x //= 2
        ans = 0
        for l in range(n):
            g = 0
            mi = inf
            t = 0
            for r in range(l, n):
                g = gcd(g, nums[r])
                if cnt[r] < mi:
                    mi = cnt[r]
                    t = 1
                elif cnt[r] == mi:
                    t += 1
                ans = max(ans, (g if t > k else g * 2) * (r - l + 1))
        return ans
