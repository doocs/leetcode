class Solution:
    def countMatchingSubarrays(self, nums: List[int], pattern: List[int]) -> int:
        n = len(nums)
        m = len(pattern)
        count = 0
        for i in range(n - m):
            flag = True
            for j in range(m):
                if (
                    (pattern[j] == 1 and nums[i + j + 1] <= nums[i + j])
                    or (pattern[j] == 0 and nums[i + j + 1] != nums[i + j])
                    or (pattern[j] == -1 and nums[i + j + 1] >= nums[i + j])
                ):
                    flag = False
                    break
            if flag:
                count += 1
        return count
