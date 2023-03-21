class Solution:
    def garbageCollection(self, garbage: List[str], travel: List[int]) -> int:
        ans = 0
        last = {}
        for i, s in enumerate(garbage):
            ans += len(s)
            for c in s:
                last[c] = i
        s = list(accumulate(travel, initial=0))
        ans += sum(s[i] for i in last.values())
        return ans
