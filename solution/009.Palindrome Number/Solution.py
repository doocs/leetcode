class Solution:
    def isPalindrome(self, num):
        """
        :type num: int
        :rtype: bool
        """
        if(num<0): return False
        
        temp = num
        rev = 0
        while(temp>0):
            rev = (rev*10)+(temp%10)
            temp//=10
        
        return (rev == num)
