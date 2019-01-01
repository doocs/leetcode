class Solution:
    def repeatedNTimes(self, A):
        """
        :type A: List[int]
        :rtype: int
        """

        count = {}
        for i in A:
            try:
                count[i] += 1
                return i
            except KeyError:
                count[i] = 0
        
