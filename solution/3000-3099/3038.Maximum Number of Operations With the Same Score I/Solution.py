class Solution:
    def maxOperations(self, nums: List[int]) -> int:
        s = nums[0] + nums[1]
        ans, n = 0, len(nums)
        for i in range(0, n, 2):
            if i + 1 == n or nums[i] + nums[i + 1] != s:
                break
            ans += 1
        return ans
