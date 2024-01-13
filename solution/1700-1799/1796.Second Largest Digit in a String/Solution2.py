class Solution:
    def secondHighest(self, s: str) -> int:
        mask = reduce(or_, (1 << int(c) for c in s if c.isdigit()), 0)
        cnt = 0
        for i in range(9, -1, -1):
            if (mask >> i) & 1:
                cnt += 1
            if cnt == 2:
                return i
        return -1
