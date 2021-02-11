class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        cnt, n = 0, len(nums)
        for i in range(n):
            if nums[i] == val:
                cnt += 1
            else:
                nums[i - cnt] = nums[i]
        return n - cnt
