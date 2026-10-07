class Solution:
    def maxEqualAdjacentPairs(self, nums: list[int]) -> int:
        cnt = defaultdict(int)
        ans = mx = 0
        for x, y in pairwise(nums):
            if x == y:
                ans += 1
            else:
                if x > y:
                    x, y = y, x
                key = x << 30 | y
                cnt[key] += 1
                mx = max(mx, cnt[key])
        ans += mx
        return ans
