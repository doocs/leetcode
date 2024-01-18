class Solution:
    def findComplement(self, num: int) -> int:
        ans = 0
        find = False
        for i in range(30, -1, -1):
            b = num & (1 << i)
            if not find and b == 0:
                continue
            find = True
            if b == 0:
                ans |= 1 << i
        return ans
