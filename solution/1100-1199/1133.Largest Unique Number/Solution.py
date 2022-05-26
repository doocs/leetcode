class Solution:
    def largestUniqueNumber(self, A: List[int]) -> int:
        counter = Counter(A)
        for i in range(1000, -1, -1):
            if counter[i] == 1:
                return i
        return -1
