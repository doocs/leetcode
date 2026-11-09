class Solution:
    def distinctAverages(self, nums: list[int]) -> int:
        nums.sort()
        ans = 0
        cnt = Counter()
        for i in range(len(nums) >> 1):
            x = nums[i] + nums[-i - 1]
            cnt[x] += 1
            if cnt[x] == 1:
                ans += 1
        return ans
