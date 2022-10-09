class Solution:
    def minMoves(self, nums: List[int], limit: int) -> int:
        d = [0] * (limit * 2 + 2)
        n = len(nums)

        for i in range(n >> 1):
            a, b = min(nums[i], nums[n - i - 1]), max(nums[i], nums[n - i - 1])

            d[2] += 2
            d[limit * 2 + 1] -= 2

            d[a + 1] -= 1
            d[b + limit + 1] += 1

            d[a + b] -= 1
            d[a + b + 1] += 1

        ans, s = n, 0
        for v in d[2 : limit * 2 + 1]:
            s += v
            if ans > s:
                ans = s
        return ans
