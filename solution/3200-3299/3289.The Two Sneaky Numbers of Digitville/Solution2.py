class Solution:
    def getSneakyNumbers(self, nums: List[int]) -> List[int]:
        n = len(nums) - 2
        xx = nums[n] ^ nums[n + 1]
        for i in range(n):
            xx ^= i ^ nums[i]
        k = xx.bit_length() - 1
        ans = [0, 0]
        for x in nums:
            ans[x >> k & 1] ^= x
        for i in range(n):
            ans[i >> k & 1] ^= i
        return ans
