class Solution:
    def reductionOperations(self, nums: List[int]) -> int:
        nums.sort()
        ans = cnt = 0
        for i, v in enumerate(nums[1:]):
            if v != nums[i]:
                cnt += 1
            ans += cnt
        return ans
