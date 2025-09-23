class Solution:
    def sortTransformedArray(
        self, nums: List[int], a: int, b: int, c: int
    ) -> List[int]:
        def f(x: int) -> int:
            return a * x * x + b * x + c

        n = len(nums)
        i, j = 0, n - 1
        ans = [0] * n
        for k in range(n):
            y1, y2 = f(nums[i]), f(nums[j])
            if a > 0:
                if y1 > y2:
                    ans[n - k - 1] = y1
                    i += 1
                else:
                    ans[n - k - 1] = y2
                    j -= 1
            else:
                if y1 > y2:
                    ans[k] = y2
                    j -= 1
                else:
                    ans[k] = y1
                    i += 1
        return ans
