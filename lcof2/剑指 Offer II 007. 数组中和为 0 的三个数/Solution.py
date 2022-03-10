class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        n, ans = len(nums), []
        nums.sort()
        for i in range(n - 2):
            if i > 0 and nums[i] == nums[i - 1]:
                continue
            left, right = i + 1, n - 1
            while left < right:
                cur = nums[i] + nums[left] + nums[right]
                if cur < 0:
                    left += 1
                elif cur > 0:
                    right -= 1
                else:
                    ans.append([nums[i], nums[left], nums[right]])
                    while left < right and nums[left] == nums[left + 1]:
                        left += 1
                    while left < right and nums[right] == nums[right - 1]:
                        right -= 1
                    left += 1
                    right -= 1
        return ans
