class Solution:
    def isPowerOfTwo(self, n):
        """
        :type n: int
        :rtype: bool
        """
        if n<=0:
            return False
        m=2**32
        if m%n:
            return False
        else:
            return True