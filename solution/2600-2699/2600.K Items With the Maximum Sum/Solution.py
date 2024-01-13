class Solution:
    def kItemsWithMaximumSum(
        self, numOnes: int, numZeros: int, numNegOnes: int, k: int
    ) -> int:
        if numOnes >= k:
            return k
        if numZeros >= k - numOnes:
            return numOnes
        return numOnes - (k - numOnes - numZeros)
