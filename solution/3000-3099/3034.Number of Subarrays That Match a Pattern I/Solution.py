class Solution:
    def countMatchingSubarrays(self, nums: List[int], pattern: List[int]) -> int:
        def f(a: int, b: int) -> int:
            return 0 if a == b else (1 if a < b else -1)

        ans = 0
        for i in range(len(nums) - len(pattern)):
            ans += all(
                f(nums[i + k], nums[i + k + 1]) == p for k, p in enumerate(pattern)
            )
        return ans
