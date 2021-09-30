class Solution:
    def numberOfSteps(self, num: int) -> int:
        res = 0
        while num:
            if (num & 1) == 0:
                num >>= 1
            else:
                num -= 1
            res += 1
        return res
