class Solution:
    def numSubarraysWithSum(self, nums: List[int], goal: int) -> int:
        i1 = i2 = s1 = s2 = j = ans = 0
        n = len(nums)
        while j < n:
            s1 += nums[j]
            s2 += nums[j]
            while i1 <= j and s1 > goal:
                s1 -= nums[i1]
                i1 += 1
            while i2 <= j and s2 >= goal:
                s2 -= nums[i2]
                i2 += 1
            ans += i2 - i1
            j += 1
        return ans
