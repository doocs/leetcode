class Solution:
    def minimumDifference(self, nums: List[int], k: int) -> int:
        ans = inf
        s = set()
        for x in nums:
            s = {x | y for y in s} | {x}
            ans = min(ans, min(abs(y - k) for y in s))
        return ans
