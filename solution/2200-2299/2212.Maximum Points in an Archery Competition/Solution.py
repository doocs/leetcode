class Solution:
    def maximumBobPoints(self, numArrows: int, aliceArrows: List[int]) -> List[int]:
        st = mx = 0
        m = len(aliceArrows)
        for mask in range(1, 1 << m):
            cnt = s = 0
            for i, x in enumerate(aliceArrows):
                if mask >> i & 1:
                    s += i
                    cnt += x + 1
            if cnt <= numArrows and s > mx:
                mx = s
                st = mask
        ans = [0] * m
        for i, x in enumerate(aliceArrows):
            if st >> i & 1:
                ans[i] = x + 1
                numArrows -= ans[i]
        ans[0] += numArrows
        return ans
