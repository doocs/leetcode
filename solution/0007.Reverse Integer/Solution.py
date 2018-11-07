class Solution:
    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """
        if x==0:
            return 0
        y=str(abs(x))
        y=y[::-1]
        y=int(y)
        if x<0:
            tmp=-y
        else:
            tmp=y
        
        if tmp>=2**31-1 or tmp<-(2**31):
            return 0
        else:
            return tmp