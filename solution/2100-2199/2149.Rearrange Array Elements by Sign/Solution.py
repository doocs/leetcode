class Solution:
    def rearrangeArray(self, nums: List[int]) -> List[int]:
        ans = [0] * len(nums)
        i, j = 0, 1
        for x in nums:
            if x > 0:
                ans[i] = x
                i += 2
            else:
                ans[j] = x
                j += 2
        return ans
