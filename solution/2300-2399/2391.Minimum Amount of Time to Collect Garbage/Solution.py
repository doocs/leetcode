class Solution:
    def garbageCollection(self, garbage: List[str], travel: List[int]) -> int:
        last = {}
        ans = 0
        for i, s in enumerate(garbage):
            ans += len(s)
            for c in s:
                last[c] = i
        ts = 0
        for i, t in enumerate(travel, 1):
            ts += t
            ans += sum(ts for j in last.values() if i == j)
        return ans
