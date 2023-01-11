class Solution:
    def maximumSum(self, nums: List[int]) -> int:
        ans = -1
        d = defaultdict(int)
        for v in nums:
            x, y = v, 0
            while x:
                y += x % 10
                x //= 10
            if y in d:
                ans = max(ans, d[y] + v)
            d[y] = max(d[y], v)
        return ans
