class Solution:
    def minInitialStrength(self, monsters: list[int], boosts: list[list[int]]) -> int:
        def check(v: int) -> bool:
            bonus = 0
            for a, b in zip(monsters, d):
                bonus += b
                if v + bonus < a:
                    return False
                v -= a
                v = max(v, 0)
            return True

        n = len(monsters)
        d = [0] * (n + 1)
        for l, r, v in boosts:
            d[l] += v
            d[r + 1] -= v

        l, r = 0, 10**15
        while l < r:
            mid = (l + r) >> 1
            if check(mid):
                r = mid
            else:
                l = mid + 1
        return l
