class Solution:
    def reachNumber(self, target: int) -> int:
        target = abs(target)
        s = k = 0
        while 1:
            if s >= target and (s - target) % 2 == 0:
                return k
            k += 1
            s += k
