class Solution:
    def strToInt(self, str: str) -> int:
        if not str:
            return 0
        n = len(str)
        if n == 0:
            return 0
        i = 0
        while str[i] == ' ':
            i += 1
            # 仅包含空格
            if i == n:
                return 0
        sign = -1 if str[i] == '-' else 1
        if str[i] in ['-', '+']:
            i += 1
        res, flag = 0, (2**31 - 1) // 10
        while i < n:
            # 非数字，跳出循环
            if not str[i].isdigit():
                break
            c = int(str[i])
            # 溢出判断
            if res > flag or (res == flag and c > 7):
                return 2**31 - 1 if sign > 0 else -(2**31)
            res = res * 10 + c
            i += 1
        return sign * res
