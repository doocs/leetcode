class Solution:
    def getModifiedArray(self, length: int, updates: List[List[int]]) -> List[int]:
        delta = [0] * length
        for start, end, inc in updates:
            delta[start] += inc
            if end + 1 < length:
                delta[end + 1] -= inc
        for i in range(1, length):
            delta[i] += delta[i - 1]
        return delta
