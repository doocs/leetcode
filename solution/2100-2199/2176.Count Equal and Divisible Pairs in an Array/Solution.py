class Solution:
    def countPairs(self, nums: List[int], k: int) -> int:
        ans = 0
        for j in range(1, len(nums)):
            for i, x in enumerate(nums[:j]):
                ans += int(x == nums[j] and i * j % k == 0)
        return ans
