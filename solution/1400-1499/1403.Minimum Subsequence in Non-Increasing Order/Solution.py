class Solution:
    def minSubsequence(self, nums: List[int]) -> List[int]:
        nums.sort(reverse=True)
        s = sum(nums)
        ans = []
        t = 0
        for v in nums:
            ans.append(v)
            t += v
            if t > s - t:
                break
        return ans
