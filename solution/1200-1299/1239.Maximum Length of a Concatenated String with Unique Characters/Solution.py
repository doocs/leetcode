class Solution:
    def maxLength(self, arr: List[str]) -> int:
        s = [0]
        for t in arr:
            x = 0
            for b in map(lambda c: ord(c) - 97, t):
                if x >> b & 1:
                    x = 0
                    break
                x |= 1 << b
            if x:
                s.extend((x | y) for y in s if (x & y) == 0)
        return max(x.bit_count() for x in s)
