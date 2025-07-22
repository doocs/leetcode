class Solution:
    def minSubarraySort(self, nums: List[int], k: int) -> List[int]:
        def f(i: int, j: int) -> int:
            mi, mx = inf, -inf
            l = r = -1
            for k in range(i, j + 1):
                if mx > nums[k]:
                    r = k
                else:
                    mx = nums[k]
                p = j - k + i
                if mi < nums[p]:
                    l = p
                else:
                    mi = nums[p]
            return 0 if r == -1 else r - l + 1

        n = len(nums)
        return [f(i, i + k - 1) for i in range(n - k + 1)]
