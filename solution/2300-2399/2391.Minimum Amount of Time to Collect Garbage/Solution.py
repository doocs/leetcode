class Solution:
    def garbageCollection(self, garbage: List[str], travel: List[int]) -> int:
        def f(c):
            tot = sum(v.count(c) for v in garbage)
            res = 0
            for i, v in enumerate(garbage):
                t = v.count(c)
                res += t
                tot -= t
                if tot == 0:
                    break
                if i < n - 1:
                    res += travel[i]
            return res

        n = len(garbage)
        return f('M') + f('P') + f('G')
