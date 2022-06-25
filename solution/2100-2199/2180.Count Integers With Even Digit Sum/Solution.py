class Solution:
    def countEven(self, num: int) -> int:
        ans = 0
        for i in range(1, num + 1):
            t = 0
            while i:
                t += i % 10
                i //= 10
            if t % 2 == 0:
                ans += 1
        return ans
