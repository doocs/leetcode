class Solution:
    def minMoves(self, nums: List[int], k: int) -> int:
        arr = [i for i, x in enumerate(nums) if x]
        s = list(accumulate(arr, initial=0))
        ans = inf
        x = (k + 1) // 2
        y = k - x
        for i in range(x - 1, len(arr) - y):
            j = arr[i]
            ls = s[i + 1] - s[i + 1 - x]
            rs = s[i + 1 + y] - s[i + 1]
            a = (j + j - x + 1) * x // 2 - ls
            b = rs - (j + 1 + j + y) * y // 2
            ans = min(ans, a + b)
        return ans
