class Solution:
    def numJewelsInStones(self, J, S):
        """
        :type J: str
        :type S: str
        :rtype: int
        """

        D = {}
        for each in J:
            D[each] = 0

        Sum = 0
        for each in S:
            if D.get(each) is not None :
                Sum += 1

        return Sum
