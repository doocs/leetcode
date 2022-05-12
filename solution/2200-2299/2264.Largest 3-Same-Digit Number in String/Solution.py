class Solution:
    def largestGoodInteger(self, num: str) -> str:
        for i in range(9, -1, -1):
            t = str(i) * 3
            if t in num:
                return t
        return ''
