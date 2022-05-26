class Solution:
    def findTheDifference(self, s: str, t: str) -> str:
        counter = Counter(s)
        for c in t:
            if counter[c] <= 0:
                return c
            counter[c] -= 1
        return None
