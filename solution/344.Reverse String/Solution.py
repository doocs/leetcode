class Solution:
    def reverseString(self, s):
        """
        :type s: str
        :rtype: str
        """
        length=len(s)
        if length < 2:
            return s
        ns=''
        p=length-1
        while p >= 0:
            ns += s[p]
            p-=1
        return ns