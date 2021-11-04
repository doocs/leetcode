class Solution:
    def isPowerOfThree(self, n: int) -> bool:
        dic = set()
        cur = 1
        dic.add(cur)
        while cur <= maxsize // 3:
            cur *= 3
            dic.add(cur)
        return n > 0 and n in dic