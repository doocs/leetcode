class Solution:
    def leastMinutes(self, n: int) -> int:
        speed = res = 1
        while speed < n:
            speed <<= 1
            res += 1
        return res
