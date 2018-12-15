class Solution:
    def judgeCircle(self, moves):
        """
        :type moves: str
        :rtype: bool
        """
        
        x = 0
        y = 0
        for each in moves:
            if( each == 'U' ):
                y += 1
            elif( each == 'D' ):
                y -= 1
            elif( each == 'R' ):
                x += 1
            elif( each == 'L' ):
                x -= 1
        
        return x == y == 0
