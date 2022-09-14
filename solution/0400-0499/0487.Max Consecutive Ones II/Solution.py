class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        ans = 1
        cnt = j = 0
        for i, v in enumerate(nums):
            if v == 0:
                cnt += 1
            while cnt > 1:
                if nums[j] == 0:
                    cnt -= 1
                j += 1
            ans = max(ans, i - j + 1)
        return ans
