class Solution:
    def maximumBobPoints(self, numArrows: int, aliceArrows: List[int]) -> List[int]:
        n = len(aliceArrows)
        state = 0
        mx = -1
        for mask in range(1 << n):
            cnt = points = 0
            for i, alice in enumerate(aliceArrows):
                if (mask >> i) & 1:
                    cnt += alice + 1
                    points += i
            if cnt <= numArrows and mx < points:
                state = mask
                mx = points
        ans = [0] * n
        for i, alice in enumerate(aliceArrows):
            if (state >> i) & 1:
                ans[i] = alice + 1
                numArrows -= ans[i]
        ans[0] = numArrows
        return ans
