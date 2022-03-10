class Solution:
    def constructArr(self, a: List[int]) -> List[int]:
        n = len(a)
        output = [1] * n
        left = right = 1
        for i in range(n):
            output[i] = left
            left *= a[i]
        for i in range(n - 1, -1, -1):
            output[i] *= right
            right *= a[i]
        return output
