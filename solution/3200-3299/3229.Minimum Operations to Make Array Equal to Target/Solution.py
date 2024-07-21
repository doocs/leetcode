class Solution:
    def minimumOperations(self, nums: List[int], target: List[int]) -> int:
        n = len(nums)
        f = abs(target[0] - nums[0])
        for i in range(1, n):
            x = target[i] - nums[i]
            y = target[i - 1] - nums[i - 1]
            if x * y > 0:
                d = abs(x) - abs(y)
                if d > 0:
                    f += d
            else:
                f += abs(x)
        return f
