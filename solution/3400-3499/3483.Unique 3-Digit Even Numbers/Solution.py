class Solution:
    def totalNumbers(self, digits: List[int]) -> int:
        s = set()
        for i, a in enumerate(digits):
            if a & 1:
                continue
            for j, b in enumerate(digits):
                if i == j:
                    continue
                for k, c in enumerate(digits):
                    if c == 0 or k in (i, j):
                        continue
                    s.add(c * 100 + b * 10 + a)
        return len(s)
