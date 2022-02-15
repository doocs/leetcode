class Solution:
    def sortTransformedArray(
        self, nums: List[int], a: int, b: int, c: int
    ) -> List[int]:
        def f(x):
            return a * x * x + b * x + c

        n = len(nums)
        i, j, k = 0, n - 1, 0 if a < 0 else n - 1
        res = [0] * n
        while i <= j:
            v1, v2 = f(nums[i]), f(nums[j])
            if a < 0:
                if v1 <= v2:
                    res[k] = v1
                    i += 1
                else:
                    res[k] = v2
                    j -= 1
                k += 1
            else:
                if v1 >= v2:
                    res[k] = v1
                    i += 1
                else:
                    res[k] = v2
                    j -= 1
                k -= 1
        return res
