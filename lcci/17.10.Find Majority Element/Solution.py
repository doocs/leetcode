class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        cnt = candidate = 0
        for num in nums:
            if cnt == 0:
                candidate = num
            cnt += (1 if candidate == num else -1)
        return candidate if nums.count(candidate) > len(nums) / 2 else -1
