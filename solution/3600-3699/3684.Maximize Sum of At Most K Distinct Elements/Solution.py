class Solution:
    def maxKDistinct(self, nums: List[int], k: int) -> List[int]:
        nums.sort()
        n = len(nums)
        ans = []
        for i in range(n - 1, -1, -1):
            if i + 1 < n and nums[i] == nums[i + 1]:
                continue
            ans.append(nums[i])
            k -= 1
            if k == 0:
                break
        return ans
