class Solution:
    def minMoves(self, nums: List[int], limit: int) -> int:
        d = [0] * (2 * limit + 2)
        n = len(nums)
        for i in range(n // 2):
            x, y = nums[i], nums[-i - 1]
            if x > y:
                x, y = y, x
            d[2] += 2
            d[x + 1] -= 2
            d[x + 1] += 1
            d[x + y] -= 1
            d[x + y + 1] += 1
            d[y + limit + 1] -= 1
            d[y + limit + 1] += 2
        return min(accumulate(d[2:]))
