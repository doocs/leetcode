class Solution(object):
    def isPalindrome(self, x):
        a = cmp(x,0)
        s = a*x
        y = int(`s`[::-1])
        return (x == y)
