class Solution:
    def countSpecialIntegers(self, nums: list[int]) -> int:
        g = defaultdict(list)
        for i, x in enumerate(nums):
            g[x].append(i)
        ans = 0
        for pos in g.values():
            if len(pos) < 3:
                continue
            d = pos[1] - pos[0]
            if all(j - i == d for i, j in pairwise(pos)):
                ans += 1
        return ans
