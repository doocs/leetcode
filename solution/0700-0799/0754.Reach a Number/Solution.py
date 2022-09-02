class Solution:
    def reachNumber(self, target: int) -> int:
        target = abs(target)
        k = s = 0
        while 1:
            if s >= target and (s - target) % 2 == 0:
                break
            k += 1
            s += k
        return k
