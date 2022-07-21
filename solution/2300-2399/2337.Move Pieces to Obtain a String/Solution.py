class Solution:
    def canChange(self, start: str, target: str) -> bool:
        a = [(v, i) for i, v in enumerate(start) if v != '_']
        b = [(v, i) for i, v in enumerate(target) if v != '_']
        if len(a) != len(b):
            return False
        for (c, i), (d, j) in zip(a, b):
            if c != d:
                return False
            if c == 'L' and i < j:
                return False
            if c == 'R' and i > j:
                return False
        return True
