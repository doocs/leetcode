class Solution:
    def myAtoi(self, s):
        """
        :type s: str
        :rtype: int
        """
        
        try:
            s = list(s.split()[0])
            Sign = {'+':1, '-':-1}[s[0]]
            s.pop(0)
        except IndexError : return 0
        except KeyError: Sign = 1

        Num = 0
        for i in s:
            try: Num = int(i) + (Num*10)
            except ValueError: break   
                
        Num = Sign*Num
        if Num > 2147483647:
            return 2147483647
        elif Num < -2147483648:
            return -2147483648
        return Num
