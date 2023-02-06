class Solution:
    def isStraight(self, nums: List[int]) -> bool:
        vis = set()
        mi, mx = inf, -inf
        for x in nums:
            if x == 0:
                continue
            if x in vis:
                return False
            vis.add(x)
            mi = min(mi, x)
            mx = max(mx, x)
        return mx - mi <= 4
