class Solution:
    def minimizeXor(self, num1: int, num2: int) -> int:
        cnt = num2.bit_count()
        ans = 0
        for i in range(30, -1, -1):
            if (num1 >> i) & 1:
                ans |= 1 << i
                cnt -= 1
                if cnt == 0:
                    return ans
        for i in range(31):
            if (num1 >> i) & 1 == 0:
                ans |= 1 << i
                cnt -= 1
                if cnt == 0:
                    return ans
        return 0
