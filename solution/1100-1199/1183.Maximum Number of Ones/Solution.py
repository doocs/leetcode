class Solution:
    def maximumNumberOfOnes(
        self, width: int, height: int, sideLength: int, maxOnes: int
    ) -> int:
        x = sideLength
        cnt = [0] * (x * x)
        for i in range(width):
            for j in range(height):
                k = (i % x) * x + (j % x)
                cnt[k] += 1
        cnt.sort(reverse=True)
        return sum(cnt[:maxOnes])
