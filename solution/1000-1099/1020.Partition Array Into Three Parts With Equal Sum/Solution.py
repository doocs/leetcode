class Solution:

    def canThreePartsEqualSum(self, A: List[int]) -> bool:
        """
        先判断是否能被3整除，如果可以，再判断是否可以至少分割成3份
        """
        s = sum(A)
        if s % 3 != 0:
            return False
        g = s / 3
        stack = 0
        c = 0
        for num in A:
            stack += num
            if stack == g:
                stack = 0
                c += 1
        return c >= 2
