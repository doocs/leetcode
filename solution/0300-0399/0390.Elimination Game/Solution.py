class Solution:
    def lastRemaining(self, n: int) -> int:
        a1, an = 1, n
        i, step, cnt = 0, 1, n
        while cnt > 1:
            if i % 2:
                an -= step
                if cnt % 2:
                    a1 += step
            else:
                a1 += step
                if cnt % 2:
                    an -= step
            cnt >>= 1
            step <<= 1
            i += 1
        return a1
