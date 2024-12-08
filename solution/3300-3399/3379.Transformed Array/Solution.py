class Solution:
    def constructTransformedArray(self, nums: List[int]) -> List[int]:
        ans = []
        n = len(nums)
        for i, x in enumerate(nums):
            ans.append(nums[(i + x + n) % n] if x else 0)
        return ans
