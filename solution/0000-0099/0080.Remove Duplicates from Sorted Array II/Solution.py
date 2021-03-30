class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        n = len(nums)
        cnt, cur = 0, 1
        for i in range(1, n):
            if nums[i] == nums[i - 1]:
                cnt += 1
            else:
                cnt = 0
            if cnt < 2:
                nums[cur] = nums[i]
                cur += 1
        return cur
