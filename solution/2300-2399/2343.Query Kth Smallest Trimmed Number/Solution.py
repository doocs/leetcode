class Solution:
    def smallestTrimmedNumbers(
        self, nums: list[str], queries: list[list[int]]
    ) -> list[int]:
        ans = []
        for k, trim in queries:
            t = sorted((v[-trim:], i) for i, v in enumerate(nums))
            ans.append(t[k - 1][1])
        return ans
