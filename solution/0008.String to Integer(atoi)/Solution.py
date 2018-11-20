class Solution:
    def myAtoi(self, s):
        """
        :type s: str
        :rtype: int
        """
        
        s = list(s.split()[0])
        Digits = {'0':0, '1':1, '2':2, '3':3, '4':4, '5':5, '6':6, '7':7, '8':8, '9':9}
        
        if s[0] in "+-":
            Sign = 1 if s[0] == '+' else -1
            s.pop(0)
        elif s[0] in Digits:
            Sign = 1
        else:
            return 0
        
        Num = 0
        for i in s:
            if i == '.':
                break
            Num *= 10
            Num += Digits[i]
        
        return Sign*Num
