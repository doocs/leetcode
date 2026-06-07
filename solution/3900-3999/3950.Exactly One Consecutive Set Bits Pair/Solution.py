class Solution:
    def consecutiveSetBits(self, n: int) -> bool:
        pre = 0
        vis = False
        while n:
            cur = n & 1
            if pre == cur == 1:
                if vis:
                    return False
                vis = True
            pre = cur
            n = n >> 1
        return vis
