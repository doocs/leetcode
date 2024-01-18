class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        f = g = h = 0
        for x in nums:
            ff = gg = hh = 0
            if x == 1:
                ff = f
                gg = min(f, g) + 1
                hh = min(f, g, h) + 1
            elif x == 2:
                ff = f + 1
                gg = min(f, g)
                hh = min(f, g, h) + 1
            else:
                ff = f + 1
                gg = min(f, g) + 1
                hh = min(f, g, h)
            f, g, h = ff, gg, hh
        return min(f, g, h)
