'''
找出T就行了。可以直接猜测。
'''


class Solution:
    def gcdOfStrings(self, str1: str, str2: str) -> str:
        len1 = len(str1)
        len2 = len(str2)
        n = min(len1, len1)
        for i in range(n - 1, -1, -1):
            if len1 % (i + 1) == 0 and len2 % (i + 1) == 0 and str1 == str1[:i + 1] * (len1 // (i + 1)) and str2 == str1[:i + 1] * (len2 // (i + 1)):
                return str1[:i + 1]
        return ''
