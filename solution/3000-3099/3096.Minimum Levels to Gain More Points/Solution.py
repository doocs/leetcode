class Solution:
    def minimumLevels(self, possible: List[int]) -> int:
        s = sum(-1 if x == 0 else 1 for x in possible)
        t = 0
        for i, x in enumerate(possible[:-1], 1):
            t += -1 if x == 0 else 1
            if t > s - t:
                return i
        return -1
