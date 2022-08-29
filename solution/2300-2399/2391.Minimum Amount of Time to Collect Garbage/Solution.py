class Solution:
    def garbageCollection(self, garbage: List[str], travel: List[int]) -> int:
        ans = 0
        pos = {}
        for i, v in enumerate(garbage):
            ans += len(v)
            for c in v:
                pos[c] = i
        s = list(accumulate(travel, initial=0))
        ans += sum(s[i] for i in pos.values())
        return ans
