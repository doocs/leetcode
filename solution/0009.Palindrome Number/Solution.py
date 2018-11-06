class Solution:
    def isPalindrome(self, num):
        """
        :type num: int
        :rtype: bool
        """
        if num < 0:
            return False
        
        return str(num) == str(num)[::-1]
