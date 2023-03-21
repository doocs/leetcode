class Solution:
    def canDivideIntoSubsequences(self, nums: List[int], k: int) -> bool:
        mx = max(len(list(x)) for _, x in groupby(nums))
        return mx * k <= len(nums)
