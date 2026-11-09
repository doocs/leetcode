class Solution:
    def constructArr(self, a: list[int]) -> list[int]:
        n = len(a)
        ans = [0] * n
        left = right = 1
        for i in range(n):
            ans[i] = left
            left *= a[i]
        for i in range(n - 1, -1, -1):
            ans[i] *= right
            right *= a[i]
        return ans
