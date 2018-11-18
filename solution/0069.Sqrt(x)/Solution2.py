# Newton's Method [python2] - 28ms

class Solution(object):
    def mySqrt(self, x):
        r = x
        while r * r > x:
            r = (r + x/r)/2
        
        return r
