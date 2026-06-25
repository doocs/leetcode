class Solution:
    def countMajoritySubarrays(self, nums: List[int], target: int) -> int:
        n = len(nums)
        ans = 0
        for i in range(n):
            cnt = 0
            for j in range(i, n):
                cnt += int(nums[j] == target)
                if cnt * 2 > j - i + 1:
                    ans += 1
        return ans
