class Solution:
    def giveGem(self, gem: List[int], operations: List[List[int]]) -> int:
        for x, y in operations:
            v = gem[x] >> 1
            gem[y] += v
            gem[x] -= v
        return max(gem) - min(gem)
