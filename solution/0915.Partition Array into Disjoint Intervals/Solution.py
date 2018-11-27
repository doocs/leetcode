class Solution:
    def partitionDisjoint(self, A):
        """
        :type A: List[int]
        :rtype: int
        """
        loc = 0
        vmx = A[0]
        mx = A[0]
        for i, el in enumerate(A):
            if el > mx:
                mx = el
            if el < vmx:
                loc = i
                vmx = mx
        return loc + 1
