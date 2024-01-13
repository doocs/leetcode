class Solution:
    def leastMinutes(self, n: int) -> int:
        speed = ans = 1
        while speed < n:
            speed <<= 1
            ans += 1
        return ans
