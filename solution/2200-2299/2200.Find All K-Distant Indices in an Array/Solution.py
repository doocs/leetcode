class Solution:
    def findKDistantIndices(self, nums: list[int], key: int, k: int) -> list[int]:
        ans = []
        n = len(nums)
        for i in range(n):
            if any(abs(i - j) <= k and nums[j] == key for j in range(n)):
                ans.append(i)
        return ans
