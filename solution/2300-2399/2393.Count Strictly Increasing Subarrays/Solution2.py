class Solution:
    def countSubarrays(self, nums: List[int]) -> int:
        ans = pre = cnt = 0
        for x in nums:
            if pre < x:
                cnt += 1
            else:
                cnt = 1
            pre = x
            ans += cnt
        return ans
