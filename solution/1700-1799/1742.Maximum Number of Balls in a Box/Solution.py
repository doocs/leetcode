class Solution:
    def countBalls(self, lowLimit: int, highLimit: int) -> int:
        counter = [0] * 60
        for i in range(lowLimit, highLimit + 1):
            s = 0
            while i:
                s += i % 10
                i //= 10
            counter[s] += 1
        return max(counter)
