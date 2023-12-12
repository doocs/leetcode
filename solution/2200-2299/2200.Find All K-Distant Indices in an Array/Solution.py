class Solution:
    def findKDistantIndices(self, nums: List[int], key: int, k: int) -> List[int]:
        ans = []
        j, n = 0, len(nums)
        for i in range(n):
            while j < i - k or (j < n and nums[j] != key):
                j += 1
            if j < n and j <= (i + k):
                ans.append(i)
        return ans
