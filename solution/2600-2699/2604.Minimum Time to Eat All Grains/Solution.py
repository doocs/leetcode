class Solution:
    def minimumTime(self, hens: List[int], grains: List[int]) -> int:
        def check(t):
            j = 0
            for x in hens:
                if j == m:
                    return True
                y = grains[j]
                if y <= x:
                    d = x - y
                    if d > t:
                        return False
                    while j < m and grains[j] <= x:
                        j += 1
                    while j < m and min(d, grains[j] - x) + grains[j] - y <= t:
                        j += 1
                else:
                    while j < m and grains[j] - x <= t:
                        j += 1
            return j == m

        hens.sort()
        grains.sort()
        m = len(grains)
        r = abs(hens[0] - grains[0]) + grains[-1] - grains[0] + 1
        return bisect_left(range(r), True, key=check)
