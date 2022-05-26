class Solution:
    def minSteps(self, s: str, t: str) -> int:
        counter = Counter(s)
        res = 0
        for c in t:
            if counter[c] > 0:
                counter[c] -= 1
            else:
                res += 1
        return res
