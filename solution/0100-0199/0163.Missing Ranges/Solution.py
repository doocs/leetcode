class Solution:
    def findMissingRanges(self, nums: List[int], lower: int, upper: int) -> List[str]:
        def f(a, b):
            return str(a) if a == b else f'{a}->{b}'

        n = len(nums)
        if n == 0:
            return [f(lower, upper)]
        ans = []
        if nums[0] > lower:
            ans.append(f(lower, nums[0] - 1))
        for a, b in pairwise(nums):
            if b - a > 1:
                ans.append(f(a + 1, b - 1))
        if nums[-1] < upper:
            ans.append(f(nums[-1] + 1, upper))
        return ans
