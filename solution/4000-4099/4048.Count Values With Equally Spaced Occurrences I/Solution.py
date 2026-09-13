class Solution:
    def countSpecialIntegers(self, nums: list[int]) -> int:
        g = defaultdict(list)
        for i, x in enumerate(nums):
            g[x].append(i)
        return sum(
            len(pos) == 3 and pos[0] + pos[2] == pos[1] * 2 for pos in g.values()
        )
