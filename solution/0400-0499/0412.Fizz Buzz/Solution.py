class Solution:
    def fizzBuzz(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        
        Ret = [str(i) for i in range(1, n+1)]
        
        for i in range(3, n+1):
            if( i%15 == 0):
                Ret[i-1] = "FizzBuzz"
            elif( i%3 == 0):
                Ret[i-1] = "Fizz"
            elif( i%5 == 0):
                Ret[i-1] = "Buzz"            
            
        return Ret
