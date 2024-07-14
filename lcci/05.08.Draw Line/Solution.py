class Solution:
    def drawLine(self, length: int, w: int, x1: int, x2: int, y: int) -> List[int]:
        ans = [0] * length
        i = (y * w + x1) // 32
        j = (y * w + x2) // 32
        for k in range(i, j + 1):
            ans[k] = -1
        ans[i] = (ans[i] & 0xFFFFFFFF) >> (x1 % 32) if x1 % 32 else -1
        ans[j] &= -0x80000000 >> (x2 % 32)
        return ans
