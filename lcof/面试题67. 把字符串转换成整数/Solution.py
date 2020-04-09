class Solution:
    def strToInt(self, str: str) -> int:
        if str is None or len(str.strip()) == 0:
            return 0
        str = str.strip()
        i = res = 0
        negative = str[0] == '-'
        i += 1 if str[0] == '-' or str[0] == '+' else 0
        while i < len(str) and str[i].isdigit():
            r = int(str[i])
            if res > (2**31 // 10) or (res == (2**31 // 10) and r > 7):
                return 2**31 - 1 if not negative else -2**31
            res = res * 10 + r
            i += 1
        return -res if negative else res