class Solution:
    def romanToInt(self, s):
        """
        :type s: str
        :rtype: int
        """
        r2i={'M':1000,'CM':900,'D':500,'CD':400,'C':100,'XC':90,'L':50,'CL':40,'X':10,'IX':9,'V':5,'IV':4,'I':1}
        i=0
        result=0
        while 1:
            if i<=(len(s)-1):
                try:
                    if r2i[s[i:(i+2)]]:
                        result += r2i[s[i:(i+2)]]
                        i=i+2
                except:
                    result += r2i[s[i]]
                    i=i+1
            else:
                break
        return result