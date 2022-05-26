class Solution:
    def convert(self, s, numRows):
        """
        :type s: str
        :type numRows: int
        :rtype: str
        """
        
        if numRows == 0:
            return ""
        elif numRows == 1:
            return s
        
        Ret = [[] for i in range(numRows)]
        i = 0
        while i < len(s):
            j = 0
            while i<len(s) and j<numRows:    # Vertical lines
                Ret[j].append(s[i])
                i+=1
                j+=1
            j -= 2
            while i<len(s) and j>0:     # Diagonal lines
                Ret[j].append(s[i])
                j-=1
                i+=1
        
        return "".join(["".join(row) for row in Ret])
            
