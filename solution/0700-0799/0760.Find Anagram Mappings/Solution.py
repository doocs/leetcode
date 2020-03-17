class Solution:
    def anagramMappings(self, A, B):
        """
        :type A: List[int]
        :type B: List[int]
        :rtype: List[int]
        """
        record = {val: i for i, val in enumerate(B)}
        return [record[val] for val in A]